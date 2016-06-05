$(document).ready(function () {
    $("select").change(function () {
        if ($('#' + $(this).attr("id") + ' option:selected').val() === '') {
            $(this).parent('div').removeClass('is-dirty');
        } else {
            $(this).parent('div').addClass('is-dirty');
        }
     });
});