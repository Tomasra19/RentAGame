$(function() {
        $('#calendar').fullCalendar({
            editable: false,
            events: "json.json"
        });
    });
