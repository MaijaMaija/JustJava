package com.example.rihards.justjava;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    /**
     * This method is called when the order button is clicked.
     */
    public void increment(View view) {
        quantity = quantity + 1;
        if (quantity > 100) {
            quantity = 100;
            Toast.makeText(this, "NOPENOPENOPE", Toast.LENGTH_SHORT).show();
        }
        displayQuantity(quantity);
    }
    /**
     * This method is called when the + button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 0) {
            Toast.makeText(getApplicationContext(), "NOPE",Toast.LENGTH_SHORT).show();
            return;
        }

        quantity = quantity - 1;
        displayQuantity(quantity);
    }
    /**
     * This method is called when the - button is clicked.
     */
    public void submitOrder(View view) {

        EditText insertName = (EditText) findViewById(R.id.insertName);


        CheckBox whippedCreamCheckbox = (CheckBox) findViewById(R.id.whippedCreamCheckbox);
        boolean hasWhippedCream = whippedCreamCheckbox.isChecked();
        CheckBox chocolateCreamCheckbox = (CheckBox) findViewById(R.id.chocolateCreamCheckbox);
        boolean hasChocolateCream = chocolateCreamCheckbox.isChecked();



        int price = calculatePrice(hasWhippedCream, hasChocolateCream);
        String priceMessage = createOrderSummary(price, hasWhippedCream, hasChocolateCream, insertName);
        displayMessage(priceMessage);

    }

    /**
     * Calculates the price of the order.
     *
     * @return total price
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolateCream) {
        int basePrice = 5;
        if (addWhippedCream) {
            basePrice = basePrice + 1;
        }
        if (addChocolateCream) {
            basePrice = basePrice +2;
        }

        return quantity * basePrice;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * This is the summary of the order.
     *
     * @param price of the order
     *              blablabla
     * @return the text summary
     */

    private String createOrderSummary(int price, boolean hasWhippedCream, boolean hasChocolateCream, EditText insertName ) {
        String priceMessage = "Name: " + insertName.getText();
        priceMessage += "\nAdd whipped cream?" + hasWhippedCream;
        priceMessage += "\nAdd chocolate cream?" + hasChocolateCream;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: $" + price;
        priceMessage += "\nThank you!";
        return priceMessage;


    }
}

