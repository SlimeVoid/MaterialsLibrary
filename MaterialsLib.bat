@echo off

set programdir=C:\Programming\Minecraft\1.5.2
set repodir=%programdir%\Git
set packagedir=%programdir%\Packages
set forgedir=%programdir%\Forge
set fmldir=%forgedir%\fml
set mcpdir=%forgedir%\mcp
set matlib=%repodir%\MaterialsLibrary
set slimelib=%repodir%\SlimevoidLibrary
cd %mcpdir%

if not exist %slimelib% GOTO :MLFAIL
if exist %matlib% GOTO :MATERIALSLIB
GOTO :MLFAIL

:MATERIALSLIB
if exist %mcpdir%\src GOTO :COPYSRC
GOTO :MLFAIL

:COPYSRC
if not exist "%mcpdir%\src-work" GOTO :CREATESRC
GOTO :MLFAIL

:CREATESRC
mkdir "%mcpdir%\src-work"
xcopy "%mcpdir%\src\*.*" "%mcpdir%\src-work\" /S
if exist "%mcpdir%\src-work" GOTO :MLCOPY
GOTO :MLFAIL

:MLCOPY
xcopy "%slimelib%\SV-common\*.*" "%mcpdir%\src\minecraft\" /S
xcopy "%matlib%\ML-source\*.*" "%mcpdir%\src\minecraft\" /S
pause
call %mcpdir%\recompile.bat
call %mcpdir%\reobfuscate.bat
echo Recompile and Reobf Completed Successfully
pause

:REPACKAGE
if not exist "%mcpdir%\reobf" GOTO :MLFAIL
if exist "%packagedir%\MaterialsLib" (
del "%packagedir%\MaterialsLib\*.*" /S /Q
rmdir "%packagedir%\MaterialsLib" /S /Q
)
mkdir "%packagedir%\MaterialsLib\slimevoid\materialslib"
xcopy "%mcpdir%\reobf\minecraft\slimevoid\materialslib\*.*" "%packagedir%\MaterialsLib\slimevoid\materialslib\" /S
xcopy "%matlib%\ML-resources\*.*" "%packagedir%\MaterialsLib\" /S
echo "Materials Library Packaged Successfully
pause
ren "%mcpdir%\src" src-old
echo Recompiled Source folder renamed
pause
ren "%mcpdir%\src-work" src
echo Original Source folder restored
pause
del "%mcpdir%\src-old" /S /Q
del "%mcpdir%\reobf" /S /Q
if exist "%mcpdir%\src-old" rmdir "%mcpdir%\src-old" /S /Q
if exist "%mcpdir%\reobf" rmdir "%mcpdir%\reobf" /S /Q
echo Folder structure reset
GOTO :MLCOMPLETE

:MLFAIL
echo Could not compile Materials Library
GOTO :MLEND

:MLCOMPLETE
echo Materials Library completed compile successfully
GOTO :MLEND

:MLEND
pause