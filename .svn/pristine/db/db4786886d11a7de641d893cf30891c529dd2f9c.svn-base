package Player;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

/**
 * 음악 재생관련 버튼, 재생바, 음량조절 있는 부분.
 * @author Hyungwook
 *
 */
class PlayPane extends GridPane {
    PlayPane() {
        Rectangle clip = new Rectangle();
        this.setClip(clip);
        clip.widthProperty().bind(this.widthProperty());
        clip.heightProperty().bind(this.heightProperty());

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(30);
        getColumnConstraints().add(0, column1);

        for (int i = 0; i < 5; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(8);
            getColumnConstraints().add(i + 1, column);
        }

        ColumnConstraints column7 = new ColumnConstraints();
        column7.setPercentWidth(30);
        getColumnConstraints().add(6, column7);

//        setStyle(" -fx-background-color: linear-gradient(to right,#1d1d1f,#31353d,#2b2e34);");
        setStyle(" -fx-background-color: linear-gradient(to right,#8C008C,#B238B0,#D95FD5);");
    }
}
