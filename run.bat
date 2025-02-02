@echo off
set JAVAFX_VERSION=17.0.9
set M2_REPO=%USERPROFILE%\.m2\repository\org\openjfx

java --module-path "%M2_REPO%\javafx-base\%JAVAFX_VERSION%\javafx-base-%JAVAFX_VERSION%-win.jar;%M2_REPO%\javafx-controls\%JAVAFX_VERSION%\javafx-controls-%JAVAFX_VERSION%-win.jar;%M2_REPO%\javafx-fxml\%JAVAFX_VERSION%\javafx-fxml-%JAVAFX_VERSION%-win.jar;%M2_REPO%\javafx-graphics\%JAVAFX_VERSION%\javafx-graphics-%JAVAFX_VERSION%-win.jar;%M2_REPO%\javafx-swing\%JAVAFX_VERSION%\javafx-swing-%JAVAFX_VERSION%-win.jar" --add-modules javafx.controls,javafx.fxml,javafx.swing,javafx.graphics -jar target/image-saver-1.0-SNAPSHOT.jar