package me.hugmanrique.emptyoptimizer.manager;

import me.hugmanrique.emptyoptimizer.Main;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Hugmanrique
 * @since 01/01/2017
 */
public class TickChanger {
    private final static String CLASS_NAME = "net.minecraft.server.MinecraftServer";

    private Main main;

    public TickChanger(Main main) {
        this.main = main;
        modifyBytecode();
    }

    private InputStream getServerBytecode() {
        return main.getServer().getClass().getClassLoader().getResourceAsStream(CLASS_NAME);
    }

    private void modifyBytecode() {
        getLogger().log(Level.INFO, "Applying ASM to Minecraft run method");

        InputStream in = getServerBytecode();

        if (in == null) {
            getLogger().log(Level.SEVERE, "Couldn't find MinecraftServer bytecode");
            return;
        }

        ClassNode classNode = new ClassNode();

        try {
            ClassReader reader = new ClassReader(in);
            reader.accept(classNode, 0);

            for (MethodNode method : (List<MethodNode>) classNode.methods) {
                if (!(method.name.equals("run") && method.desc.equals("()V"))) {
                    continue;
                }

                InsnList list = new InsnList();

                for (AbstractInsnNode node : method.instructions.toArray()) {
                    if (!(node instanceof LdcInsnNode)) {
                        list.add(node);
                        continue;
                    }

                    LdcInsnNode ldcNode = (LdcInsnNode) node;

                    if (ldcNode.cst instanceof Long && (Long) ldcNode.cst == 50L) {
                        list.add(new FieldInsnNode(Opcodes.GETSTATIC, "me/hugmanrique/emptyoptimizer/Main", "RUN_TIME", "J"));
                    }
                }

                method.instructions.clear();
                method.instructions.add(list);
            }

            writeNewBytecode(classNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeNewBytecode(ClassNode node) {
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        node.accept(writer);
    }

    private Logger getLogger() {
        return main.getLogger();
    }




}
