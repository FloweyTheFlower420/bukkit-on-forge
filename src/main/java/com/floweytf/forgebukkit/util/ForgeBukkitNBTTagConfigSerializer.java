package com.floweytf.forgebukkit.util;

import com.floweytf.forgebukkit.ForgeBukkit;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.nbt.*;
import net.minecraft.server.*;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class ForgeBukkitNBTTagConfigSerializer {
    private static final Method readArrayTag = ObfuscationReflectionHelper.findMethod(JsonToNBT.class, "func_193606_k ");
    private static final Method readKey = ObfuscationReflectionHelper.findMethod(JsonToNBT.class, "func_193601_b", String.class);

    private static final Pattern ARRAY = Pattern.compile("^\\[.*]");
    private static final Pattern INTEGER = Pattern.compile("[-+]?(?:0|[1-9][0-9]*)?i", Pattern.CASE_INSENSITIVE);
    private static final Pattern DOUBLE = Pattern.compile("[-+]?(?:[0-9]+[.]?|[0-9]*[.][0-9]+)(?:e[-+]?[0-9]+)?d", Pattern.CASE_INSENSITIVE);
    private static final JsonToNBT MOJANGSON_PARSER = new JsonToNBT(new StringReader(""));

    public static Object serialize(INBT base) {
        if (base instanceof CompoundNBT) {
            Map<String, Object> innerMap = new HashMap<>();
            for (String key : ((CompoundNBT) base).keySet()) {
                innerMap.put(key, serialize(((CompoundNBT) base).get(key)));
            }

            return innerMap;
        }
        else if (base instanceof ListNBT) {
            List<Object> baseList = new ArrayList<>();
            for (int i = 0; i < ((ListNBT) base).size(); i++)
                baseList.add(serialize((ListNBT) ((ListNBT) base).get(i)));

            return baseList;
        }
        else if (base instanceof StringNBT)
            return base.getString();
        else if (base instanceof IntNBT)
            return base.toString() + "i";

        return base.toString();
    }

    public static INBT deserialize(Object object) {
        if (object instanceof Map) {
            CompoundNBT compound = new CompoundNBT();
            for (Map.Entry<String, Object> entry : ((Map<String, Object>) object).entrySet())
                compound.put(entry.getKey(), deserialize(entry.getValue()));

            return compound;
        }
        else if (object instanceof List) {
            List<Object> list = (List<Object>) object;
            if (list.isEmpty())
                return new ListNBT();

            ListNBT tagList = new ListNBT();
            for (Object tag : list)
                tagList.add(deserialize(tag));

            return tagList;
        }
        else if (object instanceof String) {
            String string = (String) object;

            if (ARRAY.matcher(string).matches()) {
                try {
                    return (INBT) readArrayTag.invoke(new JsonToNBT(new StringReader(string)));
                }
                catch (InvocationTargetException e) {
                    throw new RuntimeException("Could not deserialize found list ", e);
                }
                catch (IllegalAccessException e) {
                    ForgeBukkit.logger.fatal(e);
                }
            }
            else if (INTEGER.matcher(string).matches())
                return IntNBT.valueOf(Integer.parseInt(string.substring(0, string.length() - 1)));
            else if (DOUBLE.matcher(string).matches())
                return DoubleNBT.valueOf(Double.parseDouble(string.substring(0, string.length() - 1)));
            else {
                INBT nbtBase = null;
                try {
                    nbtBase = (INBT) readKey.invoke(MOJANGSON_PARSER, string);
                }
                catch (IllegalAccessException | InvocationTargetException e) {
                    ForgeBukkit.logger.fatal(e);
                }

                if (nbtBase instanceof IntNBT)
                    return StringNBT.valueOf(nbtBase.getString());
                else if (nbtBase instanceof DoubleNBT)
                    return StringNBT.valueOf(String.valueOf(((DoubleNBT) nbtBase).getDouble()));
                else
                    return nbtBase;
            }
        }

        throw new RuntimeException("Could not deserialize NBTBase");
    }
}
