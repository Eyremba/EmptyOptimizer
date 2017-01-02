# EmptyOptimizer
### UPDATE: Plugin is currently broken, explanation:
This plugin tried to modify the TPS variable added by an Spigot patch ([0062-Highly-Optimized-Tick-Loop](https://hub.spigotmc.org/stash/projects/SPIGOT/repos/spigot/browse/CraftBukkit-Patches/0062-Highly-Optimized-Tick-Loop.patch)) to the net.minecraft.server.[version].MinecraftServer class. As you can see in the patch, the variable is final, therefor, not modifiable with Reflection.

Yes, I've tried to remove the final modifier by removing final from Field.modifiers of the TPS field, but as the NMS code is already compiled and final = constant, the JDK compiler simply resolves the constant for all its usages as explained here:
 
```java
public class Test {
    private final int test = 0;
    
    public void run() {
        System.out.println(test);
    }
}
```

would be converted to
```java
public class Test {    
    public void run() {
        System.out.println(0);
    }
}
```

I've also tried to modify the TPS reference in the ``run()`` method using ASM bytecode's ClassWriter, but as the server is running while the plugin tries to modify the bytecode, this is impossible to achieve.

I know another way this could be fixed, but it would imply to modify the original Spigot.jar in runtime, but I'm not sure of the legal implications this could have.

I have already asked Spigot staff to remove the plugin. I will try to make a pull request to the previously mentioned patch to see if it's possible to remove the final modifier to be accessed by using reflection.

Hugo ;)

## Old description:
Spigot transcript: [SPIGOT.md](SPIGOT.md)

You can find more info about this plugin @ [spigotmc.org](https://www.spigotmc.org/resources/emptyoptimizer.34136/)
