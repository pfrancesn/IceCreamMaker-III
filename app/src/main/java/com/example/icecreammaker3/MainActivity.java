package com.example.icecreammaker3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editName;

    TextView quantityVar, nameText, creamText, chocolateText, quantityText, priceText;

    CheckBox creamCheck, chocolateCheck;

    int priceU = 3;
    int quantity = 0;

    String name, cream, chocolate, quantityString, price;

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
        chocolateText = (TextView) findViewById(R.id.chocolateText);
        quantityText = (TextView) findViewById(R.id.quantityText);
        priceText = (TextView) findViewById(R.id.priceText);

        // CheckBox
        creamCheck = (CheckBox) findViewById(R.id.creamCheck);
        chocolateCheck = (CheckBox) findViewById(R.id.chocolateCheck);

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
            chocolate = "¿Chocolate?: Si.";
        } else {
            chocolate = "¿Chocolate?: No.";
        }
        chocolateText.setText(chocolate);
    }

    public int calculatePrice() {
        return quantity * priceU;
    }

    public void onClickButtonShowCart(View view) {
        name = "Nombre: " + editName.getText();
        nameText.setText(name);
        toppings();
        quantityString = "Cantidad: " + quantity;
        quantityText.setText(quantityString);
        price = "Total: " + calculatePrice() + " €.";
        priceText.setText(price);
        if (view.getId() == R.id.orderButton) {
            textEmail();
        }
    }

    public void textEmail() {
        EditText editEmail = (EditText) findViewById(R.id.editEmail);

        String[] emails = {editEmail.getText().toString()};
        Log.i("Patata", emails[0]);

        String subject = "Nuevo Pedido";
        String text = name + "\n" + cream + "\n" + chocolate + "\n" + quantityText + "\n" + price;

        composeEmail(emails, subject, text);

    }

    public void composeEmail(String[] email, String subject, String text) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, email[0]);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}