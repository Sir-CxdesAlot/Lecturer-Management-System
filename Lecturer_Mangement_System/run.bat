@echo off
echo Compiling Java sources...
javac --module-path "C:/Java/javafx-sdk-21.0.8/lib" --add-modules javafx.controls,javafx.fxml -d bin -cp "lib/mysql-connector-j-9.4.0.jar" src\Controller\*.java src\Data\*.java src\Model\*.java src\Util\*.java

echo Copying resources...
xcopy /Y /Q src\View\*.fxml bin\View\ >nul 2>&1
xcopy /Y /Q src\Util\*.css bin\Util\ >nul 2>&1

echo Starting application...
java --module-path "C:/Java/javafx-sdk-21.0.8/lib" --add-modules javafx.controls,javafx.fxml -cp "bin;lib/mysql-connector-j-9.4.0.jar" Controller.Main

pause
