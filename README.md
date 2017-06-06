# BouncyBlocks

Information
Bouncy Blocks allows you to make particular blocks make players bounce. This is ideal for games such as parkour. As well as allowing custom blocks for bouncing, the plugin also allows for the bounce Height to be set!

Features
Easily enable or disable bouncy blocks.
Simple and easy config/setup
Full permissions
Set up a completely custom bounce config.
Works with or without permissions! (Enable or disable it in the config)
Choose checking modes. (Scheduled), (Movement) or (Both)
Example config.yml
Scheduled Detection?: ‘both’ # Either choose True, False or Both.
Schedule Delay in ticks 20 ticks = 1 second: 20 # Choose the time between block checks.
Small Bounce Height: 1 # Set the height of the small bounces.
Large Bounce Height: 32 # Set the height of the Large bounces.
Small Bounce Blocks: 
- '4'
- '20'
Large Bounce Blocks:# Set the Large bounce height block ids.
- '19'
- '29'
Scheduled Detection Explained
- True - The Plugin checks all players every X ticks, as specified in the config. Note 20 Ticks = 1 Second.

- False - The Plugin checks players only when they move.

- Both - The Plugin checks for both

Commands / Permissions
Currently no Commands or Permissions needed! Suggestions will be taken to add more however. Currently considering adding permissions for using the bouncy blocks. Will add if suggested.

Learn by example
We would be happy for any Youtubers to make an example video of this plugin, if you're interested please get in contact! :)

Metrics
This plugin sends statistics about the usage to the server http://mcstats.org/. You can disable it using the command opt-out.