[CENTER][B][SIZE=5][COLOR=#00b300]EmptyOptimizer[/COLOR][/SIZE][/B]
[/CENTER]
This plugins allows you to change the default Minecraft tickrate and the tickrate when there's nobody connected to your server instance.

This may be useful for these scenarios:
[LIST]
[*]Lobby servers
[*]Minigame arena servers
[*]Hosted servers which aren't running a Survival world
[*]PVP servers who need to run over 20 TPS (Not recommended)
[/LIST]
This plugin will save a lot of resources if you have lots of tiny servers which are usually empty.

[B][COLOR=#ff0000]NOTE:[/COLOR][/B] Please be advised that this plugin will prevent entity ticking (movement, etc.), liquid flow, tree growth... and can have unintended Minecraft vanilla features in a Survival server.

[B]Config file:[/B]
[CODE]normalTps: 20
emptyTps: 1 # Don't use 0![/CODE]

Those values must be integers. When the server has people, it will try to run at 20 TPS, and when everybody disconnects, it will run at 1 TPS. Don't try to run the server with 0 TPS, as it will crash.

[B]Planned features:[/B]
[LIST]
[*]Unload spawnchunks
[*]Compress entity data
[*]Try to minimize the memory usage while nobody's in.
[/LIST]
The original idea came from [URL='https://www.spigotmc.org/members/devcexx.49695/']@devcexx[/URL]!

If you think this plugin was useful to you, you can donate [URL='https://hugmanrique.me/donate/']here[/URL]! :)
Source code is available on [URL='https://github.com/hugmanrique/EmptyOptimizer']GitHub[/URL]