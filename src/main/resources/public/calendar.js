$(function() {
       $('#calendar').fullCalendar( 'removeEvents');
       $('#calendar').fullCalendar({
       header: {
       left: 'prev,next today',
       center: 'title',
       right: 'month,basicWeek,basicDay'
       },
       editable: false,
       allDayDefault:true,
       eventSources: [
                       {
                           url: 'json.json'
                       }
                   ]
       });
    });
events: [
    {events}
]