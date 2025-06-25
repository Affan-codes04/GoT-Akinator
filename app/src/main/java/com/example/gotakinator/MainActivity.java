package com.example.gotakinator;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public TextView question, answer;
    public ImageView character;
    public Button yes, no;

    private Node currentNode;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        question = findViewById(R.id.question);
        answer = findViewById(R.id.answer);
        character = findViewById(R.id.character);
        yes = findViewById(R.id.yes);
        no = findViewById(R.id.no);

        // Build the decision tree
        currentNode = buildTree();

        // Set the first question
        question.setText(currentNode.question);

        yes.setOnClickListener(v -> handleResponse(true));
        no.setOnClickListener(v -> handleResponse(false));
    }

    private void handleResponse(boolean response) {
        if (response) {
            if (currentNode.yes != null) {
                currentNode = currentNode.yes;
            }
        } else {
            if (currentNode.no != null) {
                currentNode = currentNode.no;
            }
        }

        if (currentNode.isLeaf()) {
            answer.setText("Your character is: " + currentNode.question);
            answer.setVisibility(View.VISIBLE);
            yes.setVisibility(View.GONE);
            no.setVisibility(View.GONE);
        } else {
            question.setText(currentNode.question);
        }
    }

    private Node buildTree() {
        // Root question - Gender
        Node root = new Node("Is the character male?");

        // **Male Characters**
        root.yes = new Node("Is he a Stark?");
        root.yes.yes = new Node("Was he in the Night’s Watch?");
        root.yes.yes.yes = new Node("Jon Snow");
        root.yes.yes.no = new Node("Is he crippled?");
        root.yes.yes.no.yes = new Node("Bran Stark");
        root.yes.yes.no.no = new Node("Is he an honourable man?");
        root.yes.yes.no.no.yes = new Node("Lord Eddard Stark");
        root.yes.yes.no.no.no = new Node("Is he the youngest Stark?");
        root.yes.yes.no.no.no.yes = new Node("Rickon Stark");
        root.yes.yes.no.no.no.no = new Node("Was he murdered in the Red Wedding?");
        root.yes.yes.no.no.no.no.yes = new Node("Robb Stark");

        root.yes.no = new Node("Is he a Lannister?");
        root.yes.no.yes = new Node("Is he a dwarf?");
        root.yes.no.yes.yes = new Node("Tyrion Lannister");
        root.yes.no.yes.no = new Node("Is he the Kingslayer?");
        root.yes.no.yes.no.yes = new Node("Jaime Lannister");
        root.yes.no.yes.no.no = new Node("Is he the head of House Lannister?");
        root.yes.no.yes.no.no.yes = new Node("Tywin Lannister");
        root.yes.no.yes.no.no.no = new Node("Is he usually hated by everyone?");
        root.yes.no.yes.no.no.no.yes = new Node("Joffery Baratheon");
        root.yes.no.yes.no.no.no.no = new Node("Is he a bastard?");
        root.yes.no.yes.no.no.no.no.yes = new Node("Tommen Baratheon");

        root.yes.no.no = new Node("Is he a Targaryen?");
        root.yes.no.no.yes = new Node("Is he known for being cruel?");
        root.yes.no.no.yes.yes = new Node("Viserys Targaryen");
        root.yes.no.no.yes.no = new Node("Is he an ancient king?");
        root.yes.no.no.yes.no.yes = new Node("Aegon the Conqueror");

        root.yes.no.no.no = new Node("Is he a Baratheon?");
        root.yes.no.no.no.yes = new Node("Was he a rightful king?");
        root.yes.no.no.no.yes.yes = new Node("Robert Baratheon");
        root.yes.no.no.no.yes.no = new Node("Is he a bastard?");
        root.yes.no.no.no.yes.no.yes = new Node("Gendry Baratheon");

        root.yes.no.no.no.no = new Node("Is he a Greyjoy?");
        root.yes.no.no.no.no.yes = new Node("Is he a pirate king?");
        root.yes.no.no.no.no.yes.yes = new Node("Euron Greyjoy");
        root.yes.no.no.no.no.yes.no = new Node("Did he lose a part of himself?");
        root.yes.no.no.no.no.yes.no.yes = new Node("Theon Greyjoy");

        root.yes.no.no.no.no.no = new Node("Is he a Night King?");
        root.yes.no.no.no.no.no.yes = new Node("The Night King");


        root.yes.no.no.no.no.no.no = new Node("Is he a Tarly?");
        root.yes.no.no.no.no.no.no.yes = new Node("Does he aim to be a Grand Maester?");
        root.yes.no.no.no.no.no.no.yes.yes = new Node("Samwell Tarly");
        root.yes.no.no.no.no.no.no.yes.no = new Node("Is he the head of House Tarly?");
        root.yes.no.no.no.no.no.no.yes.no.yes = new Node("Randyll Tarly");
        root.yes.no.no.no.no.no.no.yes.no.no = new Node("Is he Randyll Tarly's son?");
        root.yes.no.no.no.no.no.no.yes.no.no.yes = new Node("Dickon Tarly");

        root.yes.no.no.no.no.no.no.no = new Node("Is he a Baratheon?");
        root.yes.no.no.no.no.no.no.no.yes = new Node("Did he ever become a King?");
        root.yes.no.no.no.no.no.no.no.yes.yes = new Node("Robert Baratheon");
        root.yes.no.no.no.no.no.no.no.yes.no = new Node("Was he the lord of Storm's End?");
        root.yes.no.no.no.no.no.no.no.yes.no.yes = new Node("Renly Baratheon");
        root.yes.no.no.no.no.no.no.no.yes.no.no = new Node("Did he follow R'hllor and Melisandre?");
        root.yes.no.no.no.no.no.no.no.yes.no.no.yes = new Node("Stannis Baratheon");

        root.yes.no.no.no.no.no.no.no.no = new Node("Is he Evil?");
        root.yes.no.no.no.no.no.no.no.no.yes = new Node("Is he a Bolton?");
        root.yes.no.no.no.no.no.no.no.no.yes.yes = new Node("Did he killed his Father?");
        root.yes.no.no.no.no.no.no.no.no.yes.yes.yes = new Node("Ramsay Bolton");
        root.yes.no.no.no.no.no.no.no.no.yes.yes.no = new Node("Is he a son of Roose Bolton?");
        root.yes.no.no.no.no.no.no.no.no.yes.yes.no.yes = new Node("Baby Bolton");
        root.yes.no.no.no.no.no.no.no.no.yes.yes.no.no = new Node("Has he ever been warden of the north?");
        root.yes.no.no.no.no.no.no.no.no.yes.yes.no.no.yes = new Node("Roose Bolton");

        root.yes.no.no.no.no.no.no.no.no.yes.no = new Node("Was he killed by Arya Stark?");
        root.yes.no.no.no.no.no.no.no.no.yes.no.yes = new Node("Was he given as food for his father?");
        root.yes.no.no.no.no.no.no.no.no.yes.no.yes.yes = new Node("Lother Frey");
        root.yes.no.no.no.no.no.no.no.no.yes.no.yes.no = new Node("Was he the head of a house?");
        root.yes.no.no.no.no.no.no.no.no.yes.no.yes.no.yes = new Node("Walder Frey");

        root.yes.no.no.no.no.no.no.no.no.yes.no.no = new Node("Was he named as Mad King?");
        root.yes.no.no.no.no.no.no.no.no.yes.no.no.yes = new Node("Aerys Targaryen - Mad King");

        root.yes.no.no.no.no.no.no.no.no.no = new Node("Is he a Mormont?");
        root.yes.no.no.no.no.no.no.no.no.no.yes = new Node("Did he died serving his Queen?");
        root.yes.no.no.no.no.no.no.no.no.no.yes.yes = new Node("Jorah Mormont");
        root.yes.no.no.no.no.no.no.no.no.no.yes.no = new Node("Was he a Lord Commander?");
        root.yes.no.no.no.no.no.no.no.no.no.yes.no.yes = new Node("Jeor Mormont");

        root.yes.no.no.no.no.no.no.no.no.no.no = new Node("Is he called as Little Finger?");
        root.yes.no.no.no.no.no.no.no.no.no.no.yes = new Node("Peter Baelish");
        root.yes.no.no.no.no.no.no.no.no.no.no.no = new Node("Is he the Master of Whispers?");
        root.yes.no.no.no.no.no.no.no.no.no.no.no.yes = new Node("Lord Varys");
        root.yes.no.no.no.no.no.no.no.no.no.no.no.no = new Node("Does he have a burned face?");
        root.yes.no.no.no.no.no.no.no.no.no.no.no.no.yes = new Node("Sandor Clegane - The Hound");
        root.yes.no.no.no.no.no.no.no.no.no.no.no.no.no = new Node("Is he a Zombie Knight?");
        root.yes.no.no.no.no.no.no.no.no.no.no.no.no.no.yes = new Node("Gregor Clegane - The Mountain");
        root.yes.no.no.no.no.no.no.no.no.no.no.no.no.no.no = new Node("Did he counselled Stannis Baratheon?");
        root.yes.no.no.no.no.no.no.no.no.no.no.no.no.no.no.yes = new Node("Ser Davos Seaworth");
        root.yes.no.no.no.no.no.no.no.no.no.no.no.no.no.no.no = new Node("Is he commander of the Unsullied?");
        root.yes.no.no.no.no.no.no.no.no.no.no.no.no.no.no.no.yes = new Node("Grey Worm");




        // **Female Characters**
        root.no = new Node("Is she a Stark?");
        root.no.yes = new Node("Is she a warrior?");
        root.no.yes.yes = new Node("Arya Stark");
        root.no.yes.no = new Node("Was she once married to a Lannister?");
        root.no.yes.no.yes = new Node("Sansa Stark");
        root.no.yes.no.no = new Node("Was she the mother of the Stark children?");
        root.no.yes.no.no.yes = new Node("Catelyn Stark");

        root.no.no = new Node("Is she a Lannister?");
        root.no.no.yes = new Node("Is she the Queen Regent?");
        root.no.no.yes.yes = new Node("Cersei Lannister");
        root.no.no.yes.no = new Node("Did she died of Poisoning?");
        root.no.no.yes.no.yes = new Node("Myrcella Lannister");

        root.no.no.no = new Node("Is she a Targaryen?");
        root.no.no.no.yes = new Node("Is she the Mother of Dragons?");
        root.no.no.no.yes.yes = new Node("Daenerys Targaryen");
        root.no.no.no.yes.no = new Node("Was she a dragon-rider?");
        root.no.no.no.yes.no.yes = new Node("Rhaenyra Targaryen");

        root.no.no.no.no = new Node("Is she a Tyrell?");
        root.no.no.no.no.yes = new Node("Was she married to a king?");
        root.no.no.no.no.yes.yes = new Node("Margaery Tyrell");
        root.no.no.no.no.no = new Node("Was she the Queen of Thorns?");
        root.no.no.no.no.no.yes = new Node("Olenna Tyrell");

        root.no.no.no.no.no = new Node("Is she a Martell?");
        root.no.no.no.no.no.yes = new Node("Does she seek vengeance?");
        root.no.no.no.no.no.yes.yes = new Node("Ellaria Sand");

        root.no.no.no.no.no.no = new Node("Is she Free Folk?");
        root.no.no.no.no.no.no.yes = new Node("Did she love Jon Snow?");
        root.no.no.no.no.no.no.yes.yes = new Node("Ygritte");

        root.no.no.no.no.no.no.no = new Node("Is she an Iron born?");
        root.no.no.no.no.no.no.no.yes = new Node("Yara Greyjoy");

        root.no.no.no.no.no.no.no.no = new Node("Is she a Priestess?");
        root.no.no.no.no.no.no.no.no.yes = new Node("Did she manipulated Stannis Baratheon?");
        root.no.no.no.no.no.no.no.no.yes.yes = new Node("Melisandre");
        root.no.no.no.no.no.no.no.no.yes.no = new Node("Kinvara - Meereen Priestess");

        root.no.no.no.no.no.no.no.no.no = new Node("Was she sister of Catelyn Stark?");
        root.no.no.no.no.no.no.no.no.no.yes = new Node("Lysa Arryn");

        root.no.no.no.no.no.no.no.no.no.no = new Node("Was she loyal to Bran Stark?");
        root.no.no.no.no.no.no.no.no.no.no.yes = new Node("Was she a Highborn?");
        root.no.no.no.no.no.no.no.no.no.no.yes.yes = new Node("Meera Reed");
        root.no.no.no.no.no.no.no.no.no.no.yes.no = new Node("Osha");

        root.no.no.no.no.no.no.no.no.no.no.no = new Node("Is she a Wildling?");
        root.no.no.no.no.no.no.no.no.no.no.no.yes = new Node("Is she in love with Samwell Tarly?");
        root.no.no.no.no.no.no.no.no.no.no.no.yes.yes = new Node("Gilly");

        return root;
    }

    class Node {
        String question;
        Node yes, no;

        public Node(String question) {
            this.question = question;
            this.yes = null;
            this.no = null;
        }

        public boolean isLeaf() {
            return yes == null && no == null;
        }
    }
}
