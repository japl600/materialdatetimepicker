# Material Date Time Picker

[![](https://jitpack.io/v/japl600/materialdatetimepicker.svg)](https://jitpack.io/#japl600/materialdatetimepicker)

This library want to help you, when you want to give a font to:

  - Time Picker
  - Date Picker
  - Multidate Picker

You can also:
  - Set the Light o Dark theme.
  - Set the type face of your dialog (for now only 3 typefaces: lato_regular, lato_bold and lato_italic)
  - Set OnCancelListener.

## Installation

Step 1. Add the JitPack repository to your build file

```groovy
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

Step 2. Add the dependency

```groovy
dependencies {
  implementation 'com.github.japl600:materialdatetimepicker:${version}'
}
```
# Usage
The library follows the same API as other pickers in the Android framework.
After adding the library, for using a picker in your project you need to:

1. Implement an `OnTimeSetListener`/`OnDateSetListener`
2. Create a `TimePickerDialog`/`DatePickerDialog` using the supplied factory
3. Theme the pickers

### Implement an `OnTimeSetListener`/`OnDateSetListener`
In order to receive the date or time set in the picker, you will need to implement the `OnTimeSetListener` or
`OnDateSetListener` interfaces. Typically this will be the `Activity` or `Fragment` that creates the Pickers. The callbacks use the same API as the standard Android pickers.
```java
@Override
public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
  String time = "You picked the following time: "+hourOfDay+"h"+minute;
  timeTextView.setText(time);
}

@Override
public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
  String date = "You picked the following date: "+dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
  dateTextView.setText(date);
}
```

### Create a `TimePickerDialog`/`DatePickerDialog` using the supplied factory
You will need to create a new instance of `TimePickerDialog` or `DatePickerDialog` using the static `newInstance()` method, supplying proper default values and a callback. Once the dialogs are configured, you can call `show()`.
```java
Calendar calendar = new Calendar();
DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
  MainActivity.this,
  calendar.get(Calendar,YEAR),
  calendar.get(Calendar.MONTH),
  calendar.get(Calendar.DAY_OF_MONTH)
);
datePickerDialog.show(getFragmentManager(), "Datepickerdialog");
```

### Theme the pickers
You can theme the pickers by overwriting the color resources `mdtp_accent_color` and `mdtp_accent_color_dark` in your project.
```xml
<color name="mdtp_accent_color">#009688</color>
<color name="mdtp_accent_color_dark">#00796b</color>
```

#Additional Options

* `Typeface` custom font  
The `TimePickerDialog` or `DatePickerDialog` allowed three fonts:
```java
timePickerDialog.setThemeDark("lato_regular"); //can be also lato_italic and lato_bold
```

* `TimePickerDialog` dark theme  
The `TimePickerDialog` has a dark theme that can be set by calling
```java
timePickerDialog.setThemeDark(true);
```

* `DatePickerDialog` dark theme
The `DatePickerDialog` has a dark theme that can be set by calling
```java
datePickerDialog.setThemeDark(true);
```

* `TimePickerDialog` `setTitle(String title)`
Shows a title at the top of the `TimePickerDialog`

* `OnDismissListener` and `OnCancelListener`  
Both pickers can be passed a `DialogInterface.OnDismissLisener` or `DialogInterface.OnCancelListener` which allows you to run code when either of these events occur.
```java
timePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
    @Override
    public void onCancel(DialogInterface dialogInterface) {
      Log.d("TimePicker", "Dialog was cancelled");
    }
});
```

* `vibrate(boolean vibrate)`
Set whether the dialogs should vibrate the device when a selection is made. This defaults to `true`.

#Credits 
This libary is completely based on [PersianMaterialDateTimePicker](https://github.com/mohamad-amin/PersianMaterialDateTimePicker).
