var mealAjaxUrl = "ajax/profile/meals/";

$('#startDate').datetimepicker({
    timepicker: false,
    format: 'Y-m-d'
});

$('#endDate').datetimepicker({
    timepicker: false,
    format: 'Y-m-d'
});

$('#startTime').datetimepicker({
    datepicker: false,
    format: 'H:i'
});

$('#endTime').datetimepicker({
    datepicker: false,
    format: 'H:i'
});

$('#dateTime').datetimepicker({
    format: 'Y-m-d H:i'
});


function updateFilteredTable() {
    $.ajax({
        type: "GET",
        url: mealAjaxUrl + "filter",
        data: $("#filter").serialize()
    }).done(updateTableByData);
}

function clearFilter() {
    $("#filter")[0].reset();
    $.get(mealAjaxUrl, updateTableByData);
}

$(function () {
    makeEditable({
        ajaxUrl: mealAjaxUrl,
        datatableApi: $("#datatable").DataTable({
            "ajax": {
                "url": mealAjaxUrl,
                "dataSrc": ""
            },
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "dateTime"
                },
                {
                    "data": "description"
                },
                {
                    "data": "calories"
                },
                {
                    "orderable": false,
                    "defaultContent": "",
                    "render": renderEditBtn
                },
                {
                    "orderable": false,
                    "defaultContent": "",
                    "render": renderDeleteBtn
                }
            ],
            "order": [
                [
                    0,
                    "desc"
                ]
            ],
            "createdRow": function (row, data, dataIndex) {
                if (data.excess === true) {
                    $(row).attr("data-mealExcess", true);
                } else {
                    $(row).attr("data-mealExcess", false);
                }
            }

        }),
        updateTable: function () {
            $.get(mealAjaxUrl, updateFilteredTable);
        }
    });
});