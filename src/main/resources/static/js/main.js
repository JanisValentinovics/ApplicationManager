
(function ($) {
    "use strict";


    /*==================================================================
    [ Focus Contact2 ]*/
    $('.input2').each(function(){
        $(this).on('blur', function(){
            if($(this).val().trim() != "") {
                $(this).addClass('has-val');
            }
            else {
                $(this).removeClass('has-val');
            }
        })    
    })
            
  
    
    /*==================================================================
    [ Validate ]*/
    var name = $('.validate-input input[name="name"]');
    var email = $('.validate-input input[name="email"]');
    var message = $('.validate-input textarea[name="message"]');


    $('.validate-form').on('submit',function(){
        var check = true;

        if($(name).val().trim() == ''){
            showValidate(name);
            check=false;
        }


        if($(email).val().trim().match(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/) == null) {
            showValidate(email);
            check=false;
        }

        if($(message).val().trim() == ''){
            showValidate(message);
            check=false;
        }

        return check;
    });


    $('.validate-form .input2').each(function(){
        $(this).focus(function(){
           hideValidate(this);
       });
    });

    function showValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).addClass('alert-validate');
    }

    function hideValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).removeClass('alert-validate');
    }

    mobiscroll.settings = {
        theme: 'ios',
        themeVariant: 'light',
        display: 'bubble'
    };

    var now = new Date();

    mobiscroll.calendar('#demo-calendar-mobile', {
        controls: ['calendar', 'time'],
        onInit: function (event, inst) {
            inst.setVal(now, true);
        }
    });

    mobiscroll.calendar('#demo-calendar-desktop', {
        controls: ['calendar', 'time'],
        touchUi: false,
        onInit: function (event, inst) {
            inst.setVal(now, true);
        }
    });

    mobiscroll.calendar('#demo-calendar-header', {
        controls: ['calendar', 'time'],
        headerText: '{value}',
        onInit: function (event, inst) {
            inst.setVal(now, true);
        }
    });

    mobiscroll.calendar('#demo-calendar-non-form', {
        controls: ['calendar', 'time'],
        onInit: function (event, inst) {
            inst.setVal(now, true);
        }
    });

    var instance = mobiscroll.calendar('#demo-calendar-external', {
        controls: ['calendar', 'time'],
        showOnTap: false,
        showOnFocus: false,
        onInit: function (event, inst) {
            inst.setVal(new Date(), true);
        }
    });

    document
        .getElementById('show-demo-calendar-external')
        .addEventListener('click', function () {
            instance.show();
        }, false);
    
    

})(jQuery);