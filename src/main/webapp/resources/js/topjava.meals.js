// $(document).ready(function () {
$(function () {
    makeEditable({
            ajaxUrl: "ajax/meals/",
            datatableApi: $("#datatable").DataTable({
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
                        "defaultContent": "Edit",
                        "orderable": false
                    },
                    {
                        "defaultContent": "Delete",
                        "orderable": false
                    }
                ],
                "order": [
                    [
                        0,
                        "dsc"
                    ]
                ]
            })
        }
    );
});

function getParams(url) {
    var params = "";
    if (url.includes("meals")) {
        params = "filter?" + JSON.stringify($("#filter").serialize()).replace(/\"/g, "");
    }
    return params;
}

function resetFilter() {
    $(':input','#filter')
     .not(':button, :submit, :reset, :hidden')
        .val('')
        .prop('checked', false)
        .prop('selected', false);
    updateTable();
}
