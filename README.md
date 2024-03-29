# OOP-project-1
Group [assignment](https://minersutep-my.sharepoint.com/:b:/g/personal/rjmartinez12_miners_utep_edu/EX0k4Cb8xsRIso16BqVwrh0BS_haLBnoHXKeH6rNAbA6Zg?e=UeLbLx/) for Dr. Gurijala's spring 24 object-oriented-programming class.

## JavaDoc
Docs are not added to github because they can be generated with the `javadoc -d JavaDoc *.java` command (or whatever your IDE has; Just make sure it's saved in the `JavaDoc` directory).

**DON'T commit javadoc files with your code.** I'll revert your commit if you do so. We'll generate those on demand locally when we need to submit.

## Adding JavaDoc
Change directory with: `cd src`

Then run command: `javadoc -private -version -author -d JavaDoc *.java`

The `-private` argument ensures the comments for private variables are generated.

The `-version` argument ensures the version comment is generated.

The `-author` argument ensures the author is included.




## Running the project
- `cd src`
- `javac *.java`
- `java RunShop`
