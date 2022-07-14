 function validate()
                         {
                        var field1 = document.getElementById("validationCustom03");

                        if (field1.value < 0 || field1.value > 10)
                        {
                            alert("Field1 value of " + field1.value + " is invalid");

                            // Change the value back to the previous valid answer

                            return false;
                        }

                        // Save the valid input

                        return true;
                    }