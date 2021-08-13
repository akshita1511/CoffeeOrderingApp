package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    int quantity;
    boolean whippedCream,chocolate;
    public void submitOrder(View view) {
        //int numberOfCoffee = 2;
        //display(quantity);
        //displayPrice(5 * quantity);      //2cups of coffee 5 each

    //displayMessage(createOrderSummary(quantity));

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "order summary");
        intent.putExtra(Intent.EXTRA_TEXT,createOrderSummary(quantity));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    private int calculatePrice(int quantity){
        int price=5;
        if(whippedCream){
            price+=1;
        }
        if(chocolate){
            price+=2;
        }
        return quantity*price;
    }


    private String createOrderSummary(int q){

        String s="Name: "+editText();
        checkbox();
        s+="\nadd whipped cream :"+ whippedCream;
        s+="\nadd chocolate :"+chocolate;
        s+="\nQuantity: "+quantity;
        int v=calculatePrice(q);
        s+= "\nTotal: $"+v+"\nThank You!";
        return s;
    }
    public void increment(View view){
        TextView q= (TextView) findViewById(R.id.quantity_text_view);
        quantity = Integer.parseInt(q.getText().toString());
        if(quantity==100){
            Toast toast=Toast.makeText(this,"YOU CANT ORDER MORE THAN 100 COFFEE",Toast.LENGTH_LONG);
            toast.show();
            return;
        }
        quantity++;
        displayquantity(quantity);
        //displayPrice(5*quantity);
    }

    public void decrement(View view){
        TextView q= (TextView) findViewById(R.id.quantity_text_view);
        quantity = Integer.parseInt(q.getText().toString());
        if(quantity==1){
            Toast toast= Toast.makeText(this,"COFFE CANT BE LESS THAN 1",Toast.LENGTH_LONG);
            toast.show();
            return;
        }
            quantity--;

        /*else if(quantity==0|| quantity<0){

        }*/
        displayquantity(quantity);
        //displayPrice(5*quantity);
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayquantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView OrderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        OrderSummaryTextView.setText(message);
    }
    private void checkbox(){
        CheckBox hasWhippedCream = (CheckBox) findViewById(R.id.whipped_cream);
        whippedCream =hasWhippedCream.isChecked();

        CheckBox hasChocolate = (CheckBox) findViewById(R.id.chocolate);
        chocolate = hasChocolate.isChecked();
    }
    private String editText(){
        EditText et= (EditText) findViewById(R.id.edit_text);
        String name=et.getText().toString();
        return name;
    }
}