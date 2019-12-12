var mealsAjaxUrl = "ajax/profile/meals/";

function updateFilteredTable() {
    $.ajax({
        type: "GET",
        url: mealsAjaxUrl + "filter",
        data: $('#filter').serialize()
    }).done(updateTableByData);
}

function cancelFilter() {
    $.ajax({
        type: "GET",
        url: mealsAjaxUrl,
    }).done(updateTableByData);
}

$(function () {
    makeEditable({
            ajaxUrl: mealsAjaxUrl,
            datatableApi: $('#datatable').DataTable({
                "ajax": {
                    "url": mealsAjaxUrl,
                    "dataSrc": ""
                },
                "paging": false,
                "info": true,
                "columns": [
                    {
                        "data": "dateTime",
                        "render": function (data) {
                           return data.replace("T", " ");
                        }
                    },
                    {
                        "data": "description"
                    },
                    {
                        "data": "calories",
                    },
                    {
                        "oderable": false,
                        "defaultContent": "",
                        "render": renderEditBtn
                    },
                    {
                        "oderable": false,
                        "defaultContent": "",
                        "render": renderDeleteBtn
                    },
                    {
                        "data": "excess",
                        "visible": false
                    }
                ],
                "order": [
                    [
                        0,
                        "desc"
                    ]
                ],
                "createdRow": function (row, data, dataIndex) {
                    if (data.excess) {
                        $(row).attr("data-mealExcess", true);
                    } else {
                        $(row).attr("data-mealExcess", false);
                    }
                }
            }),
            updateTable: updateFilteredTable
        }
    );
});