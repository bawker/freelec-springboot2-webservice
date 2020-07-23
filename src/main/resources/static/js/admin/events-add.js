var events_add = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function (e) {
            e.preventDefault();
            _this.save();
        });
    },
    save : function () {
        var data = {
            id: $('#id').val(),
            type: $('#type').val(),
            title: $('#title').val(),
            content: $('#content').val(),
            start_date: $('#start_date').val(),
            end_date: $('#end_date').val(),
            start_time: $('#start_time').val(),
            end_time: $('#end_time').val()
        };

        $.ajax({
            type: 'POST',
            url: '/admin/events/add',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/admin/events';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

};

events_add.init();