package com.grability.rappi.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.widget.Button;

import com.grability.rappi.R;


/**
 * Created by jorgesanmartin on 10/27/15.
 */
public class AppAlertBuilder {

    public interface IOItemSelected {
        void onItemSelected(int index, String title);

        void onCancel(int index);

        void onAccept(int index);
    }

    public static final void showAlertWithTitleAndMessage(Context context, int title, int message, int negativeButtonTitle, final IOItemSelected callback) {
        showAlertWithMessage(context, title, message, negativeButtonTitle, -1, -1, callback);
    }

    public static final void showAlertWithMessage(Context context, int message, int negativeButtonTitle, final IOItemSelected callback) {
        showAlertWithMessage(context, -1, message, negativeButtonTitle, -1, -1, callback);
    }


    public static final void showAlertWithMessage(Context context, int message, int negativeButtonTitle, int items, final IOItemSelected callback) {
        showAlertWithMessage(context, message, -1, negativeButtonTitle, -1, items, callback);
    }

    public static final void showAlertWithMessageTwoButtons(Context context, int title, int message, int negativeButtonTitle, int possitveButton, final IOItemSelected callback) {
        showAlertWithMessage(context, title, message, negativeButtonTitle, possitveButton, -1, callback);
    }

    public static final void showAlertWithMessage(final Context context, int title, String message, int negativeButtonTitle, int possitveButton, final int items, final IOItemSelected callback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.DialogStyle);
//        builder.setIcon(R.mipmap.icon_app);

        if (title != -1) {
            builder.setTitle(title);
        }

        if (message != null) {
            builder.setMessage(message);
        }

        if (items != -1) {
            builder.setItems(items, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    if (callback != null) {
                        Resources resources = context.getResources();
                        String[] items_currency = resources.getStringArray(items);
                        callback.onItemSelected(which, items_currency[which]);
                    }
                }
            });
        }

        if (negativeButtonTitle != -1) {
            builder.setNegativeButton(negativeButtonTitle, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (callback != null) {
                        callback.onCancel(which);
                    }
                }
            });
        }

        if (possitveButton != -1) {
            builder.setPositiveButton(possitveButton, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (callback != null) {
                        callback.onAccept(which);
                    }
                }
            });
        }

        AlertDialog alert = builder.create();
        alert.show();

        Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
        if (nbutton != null) {
            nbutton.setTextColor(context.getResources().getColor(R.color.gray_30));
        }
        Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);

        if (pbutton != null) {
            pbutton.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        }

    }

    public static final void showAlertWithMessage(final Context context, int title, int message, int negativeButtonTitle, int possitveButton, final int items, final IOItemSelected callback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.DialogStyle);

        if (title != -1) {
            builder.setTitle(title);
        }

        if (message != -1) {
            builder.setMessage(message);
        }

        if (items != -1) {
            builder.setItems(items, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    if (callback != null) {
                        Resources resources = context.getResources();
                        String[] items_currency = resources.getStringArray(items);
                        callback.onItemSelected(which, items_currency[which]);
                    }
                }
            });
        }

        if (negativeButtonTitle != -1) {
            builder.setNegativeButton(negativeButtonTitle, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (callback != null) {
                        callback.onCancel(which);
                    }
                }
            });
        }

        if (possitveButton != -1) {
            builder.setPositiveButton(possitveButton, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (callback != null) {
                        callback.onAccept(which);
                    }
                }
            });
        }

        AlertDialog alert = builder.create();
        alert.show();

        Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
        if (nbutton != null) {
            nbutton.setTextColor(context.getResources().getColor(R.color.gray_30));
        }
        Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);

        if (pbutton != null) {
            pbutton.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        }
    }
}
