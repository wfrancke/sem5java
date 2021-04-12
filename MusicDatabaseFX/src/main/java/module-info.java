module pl.polsl.lab.musicdatabasefx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens pl.polsl.lab.musicdatabasefx to javafx.fxml;
    exports pl.polsl.lab.musicdatabasefx;
}
