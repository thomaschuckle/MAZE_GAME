:: ---------------------------------------------------------------------
:: JAP COURSE - SCRIPT
:: SCRIPT CST8221 - Fall 2024
:: ---------------------------------------------------------------------
:: Begin of Script (Labs - F24)
:: ---------------------------------------------------------------------

CLS

:: LOCAL VARIABLES ....................................................

SET LIBDIR=Assets
SET SRCDIR=src
SET BINDIR=bin
SET BINERR=labs-javac.err
SET JARNAME=MazeGame.jar
SET JAROUT=labs-jar.out
SET JARERR=labs-jar.err
SET DOCDIR=doc
SET DOCPACK=Main
SET DOCERR=labs-javadoc.err
SET MAINCLASSSRC=src/Main/Main.java 
SET MAINCLASSBIN=Main.Main

@echo off

ECHO "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"
ECHO "@                                                                   @"
ECHO "@                   #       @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@  @"
ECHO "@                  ##       @  A L G O N Q U I N  C O L L E G E  @  @"
ECHO "@                ##  #      @    JAVA APPLICATION PROGRAMMING    @  @"
ECHO "@             ###    ##     @         F A L L  -  2 0 2 4        @  @"
ECHO "@          ###    ##        @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@  @"
ECHO "@        ###    ##                                                  @"
ECHO "@        ##    ###                 ###                              @"
ECHO "@         ##    ###                ###                              @"
ECHO "@           ##    ##               ###   #####  ##     ##  #####    @"
ECHO "@         (     (      ((((()      ###       ## ###   ###      ##   @"
ECHO "@     ((((     ((((((((     ()     ###   ######  ###  ##   ######   @"
ECHO "@        ((                ()      ###  ##   ##   ## ##   ##   ##   @"
ECHO "@         ((((((((((( ((()         ###   ######    ###     ######   @"
ECHO "@         ((         ((           ###                               @"
ECHO "@          (((((((((((                                              @"
ECHO "@   (((                      ((                                     @"
ECHO "@    ((((((((((((((((((((() ))                                      @"
ECHO "@         ((((((((((((((((()                                        @"
ECHO "@                                                                   @"
ECHO "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" 

ECHO "[LABS SCRIPT ---------------------]"

:: MOVE ASSETS FOLDER TO BIN ..........................................
ECHO "Moving Assets folder to bin directory..."
move %LIBDIR% %BINDIR%\%LIBDIR% >nul

ECHO "1. Compiling ......................"
javac -Xlint -cp ".;%SRCDIR%;%BINDIR%\%LIBDIR%/*" %MAINCLASSSRC% -d %BINDIR% 2> %BINERR%

ECHO "2. Creating Jar ..................."
cd %BINDIR%
jar cvfe %JARNAME% %MAINCLASSBIN% . > ../%JAROUT% 2> ../%JARERR%

ECHO "3. Creating Javadoc ..............."
cd ..
javadoc -cp ".;%BINDIR%;../%BINDIR%\%LIBDIR%/*" --module-path "%BINDIR%\%LIBDIR%" -d %DOCDIR% -sourcepath %SRCDIR% -subpackages %DOCPACK% 2> %DOCERR%

cd %BINDIR%
ECHO "4. Running Jar ...................."
start java --module-path "../%BINDIR%\%LIBDIR%" -jar %JARNAME%
cd ..

ECHO "[END OF SCRIPT -------------------]"
ECHO "                                   "
@echo on

:: ---------------------------------------------------------------------
:: End of Script (Labs - W24)
:: ---------------------------------------------------------------------
