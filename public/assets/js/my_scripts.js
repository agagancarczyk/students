/*
 * Displaying sign up forms for different users.
 */
$(document).ready(function() {
	$(".formStudent").hide();
	$("a#btn1").click(function() {
		$(".formStudent").toggle();
		$("body").animate({
			scrollTop : $(".formStudent").offset().top
		}, 2000);
	})
})

$(document).ready(function() {
	$(".formCollege").hide();
	$("a#btn2").click(function() {
		$(".formCollege").toggle();
		$("body").animate({
			scrollTop : $(".formCollege").offset().top
		}, 2000);
	})
})

$(document).ready(function() {
	$(".formAgency").hide();
	$("a#btn3").click(function() {
		$(".formAgency").toggle();
		$("body").animate({
			scrollTop : $(".formAgency").offset().top
		}, 2000);
	})
})

$(document).ready(function() {
	$(".formEmployer").hide();
	$("a#btn4").click(function() {
		$(".formEmployer").toggle();
		$("body").animate({
			scrollTop : $(".formEmployer").offset().top
		}, 2000);
	})
})

/*
 * Displaying searching terms as user is typing/selecting using Ajax.
 */
function showValues() {
	var fields = $(":input").serializeArray();
	$("#results").empty();
	jQuery.each(fields, function(i, field) {
		$("#results").append(field.value + " ");
	});
}
$(":text").keyup(showValues);
$(":input").keyup(showValues);
$("select").change(showValues);
showValues();
