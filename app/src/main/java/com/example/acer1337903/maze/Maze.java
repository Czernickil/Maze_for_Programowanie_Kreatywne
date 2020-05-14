package com.example.acer1337903.maze;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Maze extends AppCompatActivity {
    MazeGenerator mazeGenerator = new MazeGenerator(MainActivity.wielkosc);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maze);
        TextView tx= (TextView) findViewById(R.id.maze);

        mazeGenerator.generateMaze();
        mazeGenerator.solveMaze();
        //rozwiazujemy labirynt przed pokazaniem
        tx.setText("SYMBOLIC MAZE\n" + mazeGenerator.getSymbolicMazeOld());
        //pokazujemy labirynt bez poprawnej sciezki
    }
    public void Solve(View view) {
      mazeGenerator.solveMaze();
        TextView tx= (TextView) findViewById(R.id.maze);
        // pokazujemy labirynt ze sciezka
        tx.setText(mazeGenerator.z+ "SYMBOLIC MAZ\n" +mazeGenerator.getSymbolicMaze() );

    }
    public void GoBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
