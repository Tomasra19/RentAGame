function getDate() {
    var itemNameContent = $('.itemName:first').html();
    var arr = itemNameContent.split(': ');
    var name = $.trim(arr[1]);
    var itemPlatformContent = $('.itemPlatform').html();
    var arr1 = itemPlatformContent.split(': ');
    var platform = $.trim(arr1[1]);
    var startDate=$('#startDate').val().toString();
    var returnDate = $('#returnDate').val().toString();
        var order = {
        'name': name,
        'platform': platform,
        'startDate': startDate,
        'returnDate': returnDate
    };
    console.log(order.name + " " + order.startDate);
    $.ajax({
        type: 'POST',
        url: '/post',
        data: order,
        success: function () {
            alert('Užsakymas priimtas! Dėl užsakymo atsiėmimo kreiptis nurodytais kontaktais')
        },
        error: function () {
            alert('nepavyko!')
        }
    });
}




