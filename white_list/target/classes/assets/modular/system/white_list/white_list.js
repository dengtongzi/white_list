layui.use(['layer','upload', 'form', 'table', 'admin', 'ax', 'laydate'], function () {
    var $ = layui.$;
    var layer = layui.layer;
    var upload = layui.upload;
    var form = layui.form;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var laydate = layui.laydate;

    upload.render({
        elem: '#btnImp',
        accept: "file",
        exts: 'xlsx|xls',
        url: Feng.ctxPath + "/white_list/importFile", // 上传接口
        done: function (res) {
            console.log(res);
        },
        error: function () {
            // 请求异常回调
        }
    });
    
    /**
     * 系统管理--角色管理
     */
    var WhiteList = {
        tableId: "whiteListTable"    //表格id
    };

    /**
     * 初始化表格的列
     */
    WhiteList.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id',width: 80, sort: true, title: '编号'},
            {field: 'phone', sort: true, title: '手机号'},
            {field: 'userName', sort: true, title: '用户名'},
            {field: 'bankCardNumber',width: 250, sort: true, title: '中国银行卡号'},
            {field: 'subordinateBranch', sort: true, title: '所属分行'},
            {field: 'branchNetwork', sort: true, title: '所属支行网点'},
            {field: 'addUser', sort: true, title: '添加人'},
            {field: 'addTime', sort: true, title: '添加时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WhiteList.search = function () {
        var queryData = {};
        queryData['addTime'] = $("#addTime").val();
        queryData['phone'] = $("#phone").val();
        queryData['userName'] = $("#userName").val();
        queryData['addUser'] = $("#addUser").val();
        queryData['bankCardNumber'] = $("#bankCardNumber").val();
        queryData['subordinateBranch'] = $("#subordinateBranch").val();
        table.reload(WhiteList.tableId, {where: queryData});
    };

    /**
     * 弹出添加角色
     */
    WhiteList.openAddRole = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加角色',
            content: Feng.ctxPath + '/role/role_add',
            end: function () {
                admin.getTempData('formOk') && table.reload(WhiteList.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    WhiteList.exportExcel = function () {
        var checkRows = table.checkStatus(WhiteList.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };

    /**
     * 点击编辑角色
     *
     * @param data 点击按钮时候的行数据
     */
    WhiteList.onEditRole = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改角色',
            content: Feng.ctxPath + '/role/role_edit?roleId=' + data.roleId,
            end: function () {
                admin.getTempData('formOk') && table.reload(Role.tableId);
            }
        });
    };

    /**
     * 点击删除角色
     *
     * @param data 点击按钮时候的行数据
     */
    WhiteList.onDeleteWhiteList = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/role/remove", function () {
                Feng.success("删除成功!");
                table.reload(WhiteList.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("roleId", data.roleId);
            ajax.start();
        };
        Feng.confirm("是否删除角色 " + data.name + "?", operation);
    };

    /**
     * 分配菜单
     *
     * @param data 点击按钮时候的行数据
     */
    WhiteList.roleAssign = function (data) {
        layer.open({
            type: 2,
            title: '权限配置',
            area: ['300px', '450px'], //宽高
            fix: false,
            maxmin: true,
            content: Feng.ctxPath + '/role/role_assign/' + data.roleId,
            end: function () {
                table.reload(WhiteList.tableId);
            }
        });
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + WhiteList.tableId,
        url: Feng.ctxPath + '/white_list/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WhiteList.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        WhiteList.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        WhiteList.openAddWhiteList();
    });

    // 导出excel
    $('#btnExp').click(function () {
        WhiteList.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + WhiteList.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            WhiteList.onEditWhiteList(data);
        } else if (layEvent === 'delete') {
            WhiteList.onDeleteWhiteList(data);
        }
    });
  //日期时间范围
    laydate.render({
      elem: '#addTime'
      ,type: 'datetime'
      ,range: true
    });
});
