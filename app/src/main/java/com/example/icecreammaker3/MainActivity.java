package com.example.icecreammaker3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editName;

    TextView quantityVar, nameText, creamText;
    TextView chocolateTV;
    TextView finalQuantityTV, totalPriceTV;

    CheckBox creamCheck, chocolateCheck;

    int priceU = 3;
    int quantity = 0;

    String name, cream, chocolate, finalQuantity;


    Toast increaseToast;
    Toast decreaseToast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // EditText
        editName = (EditText) findViewById(R.id.editName);

        // TextView
        quantityVar = (TextView) findViewById(R.id.quantityVar);
        nameText = (TextView) findViewById(R.id.nameText);
        creamText = (TextView) findViewById(R.id.creamText);
//        cream = creamText.getText().toString();

        // CheckBox
        creamCheck = (CheckBox) findViewById(R.id.creamCheck);
        chocolateCheck = (CheckBox) findViewById(R.id.chocolateCheck);


        chocolateTV = (TextView) findViewById(R.id.chocolateTV);
        chocolate = chocolateTV.getText().toString();

        finalQuantityTV = (TextView) findViewById(R.id.finalQuantityTV);
        finalQuantity = finalQuantityTV.getText().toString();

        // Precio
        totalPriceTV = (TextView) findViewById(R.id.totalPriceTV);

        // Toasts
        increaseToast = Toast.makeText(getApplicationContext(), "No se pueden meter mas helados", Toast.LENGTH_SHORT);
        decreaseToast = Toast.makeText(getApplicationContext(), "No se pueden meter menos helados", Toast.LENGTH_SHORT);

    }

    public void increaseDecrease(View view) {
        if (view.getId() == R.id.increaseButton) {
            increaseQuantity();
        } else {
            decreaseQuantity();
        }
    }

    public void increaseQuantity() {
        if (quantity < 10) {
            quantity++;
            quantityVar.setText(String.valueOf(quantity));
        } else {
            increaseToast.show();
        }
    }

    public void decreaseQuantity() {
        if (quantity > 1) {
            quantity--;
            quantityVar.setText(String.valueOf(quantity));
        } else {
            decreaseToast.show();
        }
    }

    public void toppings() {
        // Check which checkbox was clicked
        if (creamCheck.isChecked()) {
            cream = "¿Crema Batida?: Sí.";
        } else {
            cream = "¿Crema Batida?: No.";
        }
        creamText.setText(cream);


        if (chocolateCheck.isChecked()) {
            chocolateTV.setText("¿Chocolate?: Si.");
        } else {
            chocolateTV.setText("¿Chocolate?: No.");

        }
    }

    public int calculatePrice() {
        return quantity * priceU;
    }

    public void onClickButtonShowCart(View view) {
        name = "Nombre: " + editName.getText();
        nameText.setText(name);
        toppings();
        finalQuantityTV.setText("Cantidad: " + quantity);
        totalPriceTV.setText("Total: " + calculatePrice() + " €.");
        if (view.getId() == R.id.orderButton) {
            textEmail();
        }
    }


//    public void onClickButtonOrder(View view) {
//        calculatePrice();
//    }

    public void textEmail() {
        EditText editEmail = (EditText) findViewById(R.id.editEmail);
//        chocolateTextView = (TextView) findViewById(R.id.chocolateTextView);
//        quantityVarTextView = (TextView) findViewById(R.id.quantityVarTextView);
//        priceTextView = (TextView) findViewById(R.id.priceTextView);
//        thxTextView = (TextView) findViewById(R.id.thxTextView);

        String[] emails = {editEmail.getText().toString()};
        Log.i("Patata", emails[0]);

        subject = "Nuevo Pedido";

        chocolate = chocolateTextView.getText().toString();
        quantityText = quantityVarTextView.getText().toString();
        price = priceTextView.getText().toString();
        thx = thxTextView.getText().toString();

        text = name + "\n" + cream + "\n" + chocolate + "\n" + quantityText + "\n" + price + "\n" + thx;

        composeEmail(email, subject, mail);

    }

    public void composeEmail(String[] email, String subject, String mail) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, email[0]);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, mail);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}