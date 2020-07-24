var events_add = {
    init : function () {
        var _this = this;
        $("#btn-save").on("click", function (e) {
            e.preventDefault();
            _this.save();
        });

        $("#btn-cancle").on("click", function (e) {
            e.preventDefault();
            _this.cancle();
        });

        $("#btn-update").on("click", function (e) {
            e.preventDefault();
            _this.update();
        });

        $("#btn-delete").on("click", function (e) {
            e.preventDefault();
            _this.delete();
        });
    },
    save : function () {
        var data = {
            type: $('input[name=type]:checked').val(),
            title: $('input[name=title]').val(),
            content: $('textarea[name=content]').val(),
            start_date: $('input[name=start_date]').val(),
            end_date: $('input[name=end_date]').val(),
            start_time: $('input[name=start_time]').val(),
            end_time: $('input[name=end_time]').val()
        };

        $.ajax({
            type: 'POST',
            url: '/admin/events/add',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('이벤트가 등록 되었습니다.');
            window.location.href = '/admin/events';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    cancle : function () {
        window.location.href = '/admin/events';
    },
    update : function () {
        var data = {
            type: $('input[name=type]:checked').val(),
            title: $('input[name=title]').val(),
            content: $('textarea[name=content]').val(),
            start_date: $('input[name=start_date]').val(),
            end_date: $('input[name=end_date]').val(),
            start_time: $('input[name=start_time]').val(),
            end_time: $('input[name=end_time]').val()
        };

        var id = $('input[name=id]').val();

        $.ajax({
            type: 'PUT',
            url: '/admin/events/update/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('이벤트가 수정 되었습니다.');
            window.location.href = '/admin/events';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete : function () {
        var id = $('input[name=id]').val();

        $.ajax({
            type: 'DELETE',
            url: '/admin/events/delete/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
        }).done(function() {
            alert('이벤트가 삭제 되었습니다.');
            window.location.href = '/admin/events';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

};

events_add.init();