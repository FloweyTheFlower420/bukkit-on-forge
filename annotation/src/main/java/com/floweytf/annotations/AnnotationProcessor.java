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
                    if(!checkMethod(classElement))
                        processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Class does not have toMinecraft and toBukkit methods with correct parameters", classElement);
                }
            }
        }

        return true;
    }

    public boolean checkMethod(TypeElement typeElement) {
        ExecutableElement toMinecraftMethod = null;
        ExecutableElement toBukkitMethod = null;

        for(Element methodElement : typeElement.getEnclosedElements()) {
            if (methodElement instanceof ExecutableElement) {
                if(checkMethodMinecraft((ExecutableElement) methodElement)) {
                    if(toMinecraftMethod != null)
                        return false;
                    toMinecraftMethod = (ExecutableElement) methodElement;
                }

                if(checkMethodBukkit((ExecutableElement) methodElement)) {
                    if(toBukkitMethod != null)
                        return false;
                    toBukkitMethod = (ExecutableElement) methodElement;
                }
            }
        }

        if(toMinecraftMethod == null || toBukkitMethod == null)
            return false;

        return
            processingEnv.getTypeUtils().isSameType(toMinecraftMethod.getReturnType(), getParam(toBukkitMethod)) &&
            processingEnv.getTypeUtils().isSameType(toBukkitMethod.getReturnType(), getParam(toMinecraftMethod));
    }

    public boolean checkMethodMinecraft(ExecutableElement method) {
        if(!method.getSimpleName().toString().equals("toMinecraft"))
            return false;

        if(method.getParameters().size() != 1)
            return false;

        return true;
    }

    public boolean checkMethodBukkit(ExecutableElement method) {
        if(!method.getSimpleName().contentEquals("toBukkit"))
            return false;

        if(method.getParameters().size() != 1)
            return false;

        return true;
    }

    public static TypeMirror getParam(ExecutableElement method) {
        return method.getParameters().get(0).asType();
    }

    public static TypeElement getElement(TypeMirror mirror) {
        if(mirror.getKind() == TypeKind.DECLARED)
            return (TypeElement) ((DeclaredType)mirror).asElement();
        System.out.println("WTF");
        System.out.println(mirror.getKind().toString());
        for (StackTraceElement ste : Thread.currentThread().getStackTrace()) {
            System.out.println(ste);
        }
        return null;
    }
}