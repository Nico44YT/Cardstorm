package nico.cardstorm.fx;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class BlocklyView extends JPanel {

    private final JFXPanel fxPanel;
    private WebEngine webEngine;
    private Consumer<String> onJsonReceived;

    public BlocklyView() {
        super(new BorderLayout());
        fxPanel = new JFXPanel(); // JavaFX inside Swing
        add(fxPanel, BorderLayout.CENTER);

        initFX();
    }

    /**
     * Initialize JavaFX WebView and load Blockly.
     */
    private void initFX() {
        Platform.runLater(() -> {
            WebView webView = new WebView();
            webEngine = webView.getEngine();

            // Load local Blockly HTML
            webEngine.load(getClass().getResource("/blockly/index.html").toExternalForm());

            // Expose JavaConnector to JS
            JSObject window = (JSObject) webEngine.executeScript("window");
            window.setMember("javaConnector", new JavaConnector());

            fxPanel.setScene(new Scene(webView));
        });
    }

    /**
     * Sets a listener that receives Blockly JSON when the user triggers it.
     * @param listener Consumer that receives JSON string
     */
    public void setOnJsonReceived(Consumer<String> listener) {
        this.onJsonReceived = listener;
    }

    /**
     * Call this to ask Blockly to send its JSON to Java.
     */
    public void requestBlocklyJson() {
        Platform.runLater(() -> {
            if (webEngine != null) {
                webEngine.executeScript("sendToJava()");
            }
        });
    }

    /**
     * Exposed to JavaScript to receive JSON from Blockly.
     */
    public class JavaConnector {
        public void receiveJSON(String json) {
            if (onJsonReceived != null) {
                SwingUtilities.invokeLater(() -> onJsonReceived.accept(json));
            }
        }
    }

    // Optional helper: execute arbitrary JS
    public void executeJS(String script) {
        Platform.runLater(() -> webEngine.executeScript(script));
    }
}