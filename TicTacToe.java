package tictactoe;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;


	//size of window
	public class TicTacToe extends Application {
		
		private boolean playable = true;
		private boolean turnX = true;
		private Tile[][] board = new Tile[3][3];
		private List<Inarow> inarows = new ArrayList<>();
		
		//creates scene
		private Parent createContent() {
			Pane root = new Pane();
			root.setPrefSize(999, 999);
			
			for (int i = 0; i < 3; i ++) {
				for (int a = 0; a < 3; a++) {
					
					Tile tile = new Tile();
					tile.setTranslateX(a * 333);
					tile.setTranslateY(i * 333);
					
					root.getChildren().add(tile);
					
					//assigns tiles to array
					board[a][i] = tile;

				}
					  
			}
			//horizontal win
			for(int y = 0; y < 3; y++) {
				inarows.add(new Inarow(board [0][y], board[1][y], board[2][y]));
			}
			//vertical win
			for(int x = 0; x < 3; x++) {
				inarows.add(new Inarow(board [x][0], board[x][1], board[x][2]));
			}
			//diag win
				inarows.add(new Inarow(board [0][0], board[1][1], board[2][2]));
				inarows.add(new Inarow(board [2][0], board[1][1], board[0][2]));
			return root;
		}
		//Inside window
		public void start(Stage mainStage) throws Exception {
			mainStage.setScene(new Scene(createContent()));
			mainStage.show();
		}
		//checks for a win
		private void gameover() {
			for(Inarow inarow : inarows) {
				if (inarow.isComplete()) {
					Text text = new Text();
					//game end text
	
					playable = false;
					break;
				}
			}
		}
		
		//win tracker
		private class Inarow {
			private Tile[] tiles;
			public Inarow(Tile... tiles) {
				this.tiles = tiles;
		}
			public boolean isComplete() {
				if (tiles[0].value().isEmpty())
	                return false;

	            return tiles[0].value().equals(tiles[1].value())
	                    && tiles[0].value().equals(tiles[2].value());
			}
		}
		// color of lines and size or rectangle
		private class Tile extends StackPane {
			private Text text = new Text();
			//game over text
			public Tile() {
				Rectangle border = new Rectangle(333, 333);
				border.setFill(null);
				border.setStroke(Color.BLACK);
				text.setFont(Font.font(200));
				//aligns x and 0 to center of square
				setAlignment(Pos.CENTER);
				//calls text, which adds x and 0
				getChildren().addAll(border, text);
				
				//mouse triggers

	            setOnMouseClicked(event -> {
	                if (!playable)
	                    return;
	                if (event.getButton() == MouseButton.PRIMARY) {
	                    if (!turnX)
	                        return;
	                    //calls method to draw x, then changes turn
	                    drawX();
	                    turnX = false;
	                    gameover();
	                }
	                else if (event.getButton() == MouseButton.SECONDARY) {
	                    if (turnX)
	                        return;
	                    //calls method to draw 0, then changes turn
	                    draw0();
	                    turnX = true;
	                    gameover();
	                }
	            });
			}
			
	
			//Checks for 3 in a row
			public String value() {
				return text.getText();
			}
			// CLICKING WILL BECOME....
			//the x
			private void drawX() {
				text.setFill(Color.RED);
				text.setText("X");	
			}
			
			//the o
			private void draw0() {
				text.setFill(Color.BLUE);
				text.setText("0");
			}
		}
		
		public static void main(String[] args) {
			launch(args);
		}
	}
