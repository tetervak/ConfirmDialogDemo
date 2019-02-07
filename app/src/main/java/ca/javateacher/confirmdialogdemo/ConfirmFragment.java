/* Alex Tetervak, Sheridan College, Ontario */
package ca.javateacher.confirmdialogdemo;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

public class ConfirmFragment extends DialogFragment {

  private static final String MESSAGE = "message";
  private static final String ID = "id";

  private int mDialogID;

  interface ConfirmListener {
    void onConfirmed(int dialogID);
  }
  private ConfirmListener mConfirmListener;


  public ConfirmFragment() {
    // Required empty public constructor
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof ConfirmListener) {
      mConfirmListener = (ConfirmListener) context;
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mConfirmListener = null;
  }

  public static ConfirmFragment newInstance(int dialogID, String message) {
    ConfirmFragment fragment = new ConfirmFragment();
    Bundle arguments = new Bundle();
    arguments.putInt(ID, dialogID);
    arguments.putString(MESSAGE, message);
    fragment.setArguments(arguments);
    return fragment;
  }

  @SuppressWarnings("ConstantConditions")
  @Override
  @NonNull
  public Dialog onCreateDialog(@Nullable Bundle bundle) {

    // get the arguments
    mDialogID = 0;
    String message = MESSAGE;
    Bundle arguments = getArguments();
    if(arguments != null){
      mDialogID = arguments.getInt(ID);
      message = arguments.getString(MESSAGE);
    }

    // create a new AlertDialog Builder
    AlertDialog.Builder builder =
        new AlertDialog.Builder(getActivity());
    builder.setTitle(R.string.app_name);
    builder.setMessage(message);

    builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int button) {
            if (mConfirmListener != null) {
              mConfirmListener.onConfirmed(mDialogID);
            }
          }
        }
    );

    builder.setNegativeButton(android.R.string.no, null);
    return builder.create(); // return the AlertDialog
  }

}
