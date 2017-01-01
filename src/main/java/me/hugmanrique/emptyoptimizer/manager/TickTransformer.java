package me.hugmanrique.emptyoptimizer.manager;

import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.tree.*;
import me.hugmanrique.emptyoptimizer.Main;

import java.util.Iterator;

/**
 * @author Hugmanrique
 * @since 01/01/2017
 */
public class TickTransformer {
    private Main main;

    public TickTransformer(Main main) {
        this.main = main;
    }

    private byte[] patchServerTickrate(byte[] bytes) {
        ClassNode classNode = new ClassNode();
        ClassReader reader = new ClassReader(bytes);
        reader.accept(classNode, 0);

        Iterator<MethodNode> methods = classNode.methods.iterator();

        while (methods.hasNext()) {
            MethodNode method = methods.next();

            if ((method.name.equals("run")) && (method.desc.equals("()V"))) {
                InsnList list = new InsnList();

                for (AbstractInsnNode node : method.instructions.toArray()) {
                    if (node instanceof LdcInsnNode) {
                        LdcInsnNode ldcNode = (LdcInsnNode) node;

                        if ((ldcNode.cst instanceof Long) && ((Long) ldcNode.cst == 50L)) {
                            list.add(new FieldInsnNode(Opcodes.GETSTATIC, "me/hugmanrique/emptyoptimizer/manager/TickChanger", "MILLISECONDS_PER_TICK", "J"));
                            continue;
                        }
                    }

                    list.add(node);
                }

                method.instructions.clear();
                method.instructions.add(list);
            }
        }

        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        classNode.accept(writer);
        return writer.toByteArray();
    }
}
