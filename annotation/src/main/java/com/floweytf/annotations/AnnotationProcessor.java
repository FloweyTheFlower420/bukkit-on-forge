package com.floweytf.annotations;

import com.google.auto.service.AutoService;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import java.util.List;
import java.util.Set;


@SupportedAnnotationTypes("com.floweytf.forgebukkit.util.Converter")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class AnnotationProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement annotation : annotations) {

            Set<? extends Element> rootElement = roundEnv.getElementsAnnotatedWith(annotation);
            for(Element e: rootElement) {
                if(e.getKind() == ElementKind.CLASS) {
                    TypeElement classElement = (TypeElement) e;
                    String className = e.getSimpleName().toString();
                    List<? extends javax.lang.model.type.TypeMirror> interfaceElement = classElement.getInterfaces();
                    if(interfaceElement.size() == 0)
                        processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Class does not extend bukkit class", classElement);
                    TypeMirror bukkitInterfaceClass = null;
                    for(TypeMirror iter : interfaceElement)
                        if(((TypeElement)((DeclaredType)iter).asElement()).getQualifiedName().toString().startsWith("org.bukkit"))
                            bukkitInterfaceClass = iter;

                    if(bukkitInterfaceClass == null)
                        processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Converter cannot contain no implemented interface from org.bukkit", classElement);

                    DeclaredType superClassTypeMirror = (DeclaredType)classElement.getSuperclass();

                    if(superClassTypeMirror.getKind() == TypeKind.NONE)
                        processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Converter must have Wrapper<T> as superclass", classElement);

                    if(!((TypeElement)superClassTypeMirror.asElement()).getQualifiedName().toString().equals("com.floweytf.forgebukkit.Wrapper"))
                        processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Converter must have Wrapper<T> as superclass", classElement);

                    TypeElement wrapperTypeParamElement = (TypeElement)(((DeclaredType)superClassTypeMirror.getTypeArguments().get(0)).asElement());

                    if(!wrapperTypeParamElement.getQualifiedName().toString().startsWith("net.minecraft"))
                        processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Superclass is not Wrapper<net.minecraft.*>", classElement);

                    if(!checkMethod(classElement, roundEnv, (TypeElement) ((DeclaredType)bukkitInterfaceClass).asElement(), wrapperTypeParamElement))
                        processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Class does not have toMinecraft and toBukkit methods with correct parameters", classElement);
                }
            }
        }

        return true;
    }

    public static boolean checkMethod(TypeElement typeElement, RoundEnvironment roundEnv, TypeElement bukkitType, TypeElement minecraftType) {
        boolean hasToMinecraft = false;
        boolean hasToBukkit = false;

        for(Element methodElement : typeElement.getEnclosedElements()) {
            if (methodElement instanceof ExecutableElement) {
                if(checkMethodMinecraft((ExecutableElement) methodElement, roundEnv, bukkitType, minecraftType))
                    hasToMinecraft = true;
                if(checkMethodBukkit((ExecutableElement) methodElement, roundEnv, bukkitType, minecraftType))
                    hasToBukkit = true;
            }
        }

        return hasToMinecraft && hasToBukkit;
    }

    public static boolean checkMethodMinecraft(ExecutableElement method, RoundEnvironment roundEnv, TypeElement bukkitType, TypeElement minecraftType) {
        if(!method.getSimpleName().toString().equals("toMinecraft"))
            return false;

        if(method.getParameters().size() != 1)
            return false;

        if(!method.getReturnType().equals(minecraftType.asType()))
            return false;

        return method.getParameters().get(1).asType().equals(bukkitType.asType());
    }

    public static boolean checkMethodBukkit(ExecutableElement method, RoundEnvironment roundEnv, TypeElement bukkitType, TypeElement minecraftType) {
        if(!method.getSimpleName().toString().equals("toBukkit"))
            return false;

        if(method.getParameters().size() != 1)
            return false;

        if(!method.getReturnType().equals(bukkitType.asType()))
            return false;

        return method.getParameters().get(1).asType().equals(minecraftType.asType());
    }
}