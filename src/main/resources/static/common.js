/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * build left top block content
 */
$(document).ready(function () {
    $('#leftBlockContent').treeview({data: getTreeData(), showTags: true});
    $('#leftBlockContent').treeview('collapseAll', {silent: true});
});

/**
 * bootStrap treeview data
 * categories data
 * @returns {Array}
 */
function getTreeData() {
    let ajaxResult = $.ajax({url: "/api/category", async: false});

    return ajaxResult.responseText;
}

//add event for square class elements
$(document).ready(function () {
    $('.square').click(function () {
        var productInfo = new Object;
        productInfo.name = $(this).find('span').eq(0).text();
        productInfo.price = $(this).find('span').eq(1).text();
        productInfo.in_stock = parseInt($(this).find('span').eq(2).text());
        productInfo.purchaseQuantity = 0;
        console.log(productInfo);
        var selectCode = '<div class="quantity"><span class="glyphicon glyphicon-minus-sign" onclick="purchasesQuantityChange(\'reduce\')"></span><span id="purchaseQuantity">0</span><span class="glyphicon glyphicon-plus-sign" onclick="purchasesQuantityChange(\'increase\')"></span></div>';
        var submitCode = '<button class="btn btn-info" onclick="updatePurchasesList()" disabled>Submit</button>';
        $('#payHintFront').find('span').eq(1).html('<h1>' + productInfo.name + '</h1>' + '<h3>' + productInfo.price + '</h3>' + '<h4>' + productInfo.in_stock + '</h4>' + selectCode + submitCode);
        hintShow();
    });
});

//add event for remove icon
$(document).ready(function () {
    $('#hintRemove').click(function () {
        hintHide();
    });
});

//hide hint block
function hintShow() {
    $('#payHintBg').show();
    $('#payHintFront').show();
}

//show hint block
function hintHide() {
    $('#payHintBg').hide();
    $('#payHintFront').hide();
}

//库存变更
function purchasesQuantityChange(operate) {
    if (operate === 'increase') {
        if (parseInt($('#purchaseQuantity').text()) >= parseInt($('#productUnitQuantity').find('span').eq(1).text()))
            return;
        $('#purchaseQuantity').text(parseInt($('#purchaseQuantity').text()) + 1);
        $(".quantityDivision button").prop('disabled', '');
    } else if (operate === 'reduce') {
        if (parseInt($('#purchaseQuantity').text()) <= 0) {
            return;
        }
        $('#purchaseQuantity').text(parseInt($('#purchaseQuantity').text()) - 1);
        if (parseInt($('#purchaseQuantity').text()) <= 0) {
            $(".quantityDivision button").prop('disabled', 'disabled');
        }
    }
}

function getHint_productInfo() {
    var productInfo = new Object;
    productInfo.name = $('#productName').text();
    productInfo.id = $('#productId').text();
    productInfo.price = $('#productUnitPrice').find('span').eq(1).text();
    productInfo.purchaseQuantity = $('#purchaseQuantity').text();
    return productInfo;
}

//购物车追加内容
function updatePurchasesList() {
    var productInfo = getHint_productInfo();
    $('#rightBottomBlock', window.top.document).append('<div class="productInfo"><span class="glyphicon glyphicon-remove-circle" onclick="removePurchase($(this))"></span><span class="productInfo_name">' + productInfo.name + '</span><span class="productInfo_price">' + productInfo.price + '</span><span class="productInfo_purchases">x' + productInfo.purchaseQuantity + '</span><span style="display: none" class="productInfo_id">' + productInfo.id + '</span></div>');
    updateTotalPay(productInfo.price * productInfo.purchaseQuantity);
    hintHide();
    //$('#rightBottomBlockHint').hide();
    $('#rightBottomBlock #rightBottomBlockHint', window.top.document).remove();
    $('.btn.btn-success', window.top.document).prop('disabled', '');
}


function updateTotalPay(price) {
    console.log('total pay updated');
    console.log(strip(numberAdd(price, $('#payCount').text())));
    $('#payCount', window.top.document).text(strip(numberAdd(price, $('#payCount', window.top.document).text())));
}

//构造收件人信息填写页面
function paymentReady() {
    var submitCode = '<button class="btn btn-info" onclick="paymentSubmit()">Submit</button>';
    var contentCode = `<div id="userInfo"><div class="input-group">
  <span class="input-group-addon" id="basic-addon1">姓名</span>
  <input id="name"  type="text" class="form-control" placeholder="收件人姓名" aria-describedby="basic-addon1" required>
</div>
    
  <div class="input-group">
  <span class="input-group-addon" id="basic-addon2">地址</span>
  <input id="userAddress" type="text" class="form-control" placeholder="收件地址" aria-describedby="basic-addon2">
</div>

<div class="input-group">
  <span class="input-group-addon" id="basic-addon2">邮箱</span>
  <input id="userEmail" type="text" class="form-control" placeholder="邮箱地址" aria-describedby="basic-addon2">
</div>

</div>`;
    $('#payHintFront').find('span').eq(1).html(contentCode + submitCode);
    hintShow();
}

//预提交结算
function paymentSubmit() {
    if ($('#name').val() == '') {
        $('#name_last').focus();
        return;
    }
    if ($('#userEmail').val() == '') {
        $('#userEmail').focus();
        return;
    }
    if ($('#userAddress').val() == '') {
        $('#userAddress').focus();
        return;
    }
    let userInfo = new Object();
    userInfo.name=$('#name').val();
    userInfo.email=$('#userEmail').val();
    userInfo.address=$('#userAddress').val();
    userInfo.list = new Array();

    var productInfoList = $('.productInfo');

    productInfoList.each(function (i, obj) {
        var obj_temp = new Object();
        obj_temp.id = $(this).find('.productInfo_id').text();
        obj_temp.purchaseQuantity = $(this).find('.productInfo_purchases').text().slice(1);
        userInfo.list.push(obj_temp);
    });
    console.log(userInfo);

    //结算
    let ajaxResult = $.ajax({url: "/api/addExchange_list", async: false,data:JSON.stringify(userInfo),type:"POST",contentType:"application/json"});

    $('#payHintFront').find('span').eq(1).html('<h1>购买成功</h1>');

    return;
    $.each(productPurchaseList, function (i, obj) {
        productsInfoContent += obj.name + ' ' + obj.purchaseQuantity + "\n";
    });
    productsInfoContent += "\nTotal Pay: " + $('#payCount', window.top.document).text() + "\n\n";
    productsInfoContent += "The address you choose is: " + $('#userAddress').val() + "\n\nThank you.";

    var post_data = 'title=' + encodeURI(post_title) + '&content=' + encodeURI(post_content + productsInfoContent) + '&toemail=' + $('#userEmal').val();
    console.log(post_data);
    $.post('./phpmail/sendmail.php', post_data, function (data, status) {
        console.log(data);
        console.log(status);
    });
    location.reload();
}

//js加法
function numberAdd(num1, num2) {
    const num1Digits = (num1.toString().split('.')[1] || '').length;
    const num2Digits = (num2.toString().split('.')[1] || '').length;
    const baseNum = Math.pow(10, Math.max(num1Digits, num2Digits));
    return (num1 * baseNum + num2 * baseNum) / baseNum;
}

//js减法
function numberMinus(num1, num2) {
    const num1Digits = (num1.toString().split('.')[1] || '').length;
    const num2Digits = (num2.toString().split('.')[1] || '').length;
    const baseNum = Math.pow(10, Math.max(num1Digits, num2Digits));
    return (num1 * baseNum - num2 * baseNum) / baseNum;
}

//js小数精度
function strip(num, precision) {
    precision = precision || 12;
    return +parseFloat(num.toPrecision(precision));
}

//从购物车中删除商品
function removePurchase(obj) {
    let price = parseInt(obj.parent().find('.productInfo_purchases').text().slice(1)) * obj.parent().find('.productInfo_price').text();
    console.log(price);
    $('#payCount').text(strip(numberMinus($('#payCount').text(), price)));
    obj.parent().remove();
    if ($('#payCount').text() == 0) {
        $('#counter button').prop('disabled', 'disabled');
    }
}

var ignoreNodeId = null;
$(document).ready(function () {
    $('#leftBlockContent').on('nodeCollapsed', function (event, node) {
        window.ignoreNodeId = node.nodeId;
    });
});


//向server发起get请求获取商品信息[JSON]
$(document).ready(function () {
    //分类商品选中触发跳转事件
    $('#leftBlockContent').on('nodeSelected', function (event, node) {
        var parentId = node.parentId;
        var parentNodeObj = $('#leftBlockContent').treeview('getNode', parentId);
        //var url = './product.html?';
        var productInfoURL = './api/product/' + encodeURI(node.id) + '?';
        //发起ajax请求
        $.ajax({
            url: productInfoURL, dataType: 'json',
            success: function (data) {
                console.log(data);
                showProductInfo(data);
            },
            error: function () {
                console.log("get product informaion failed")
                //TODO 提示请重新刷新[商品详细信息获取失败]
            }
        });
    });
});

//渲染商品详细信息页面
function showProductInfo(productInfo) {
    let htmlContent = "<h1 id='productName'>" + productInfo.name + "</h1>";
    htmlContent += "<img src='" + productInfo.pic + "' alt=" + productInfo.name + ">"
    //TODO insert image url htmlContent +=
    htmlContent += "<h2 id='productUnitQuantity'><span class='desc'>库存:</span><span>" + productInfo.stock + "</span></h2>";
    htmlContent += "<h3 id='productUnitPrice'><span class='desc'>单价:</span><span>" + productInfo.price + "</span></h3>";
    htmlContent += "<span style='display:none;' id='productId'>" + productInfo.id + "</span>";
    htmlContent += "<div class='quantityDivision'>";
    htmlContent += "<div class='quantity'><span class='glyphicon glyphicon-minus-sign' onclick=\"purchasesQuantityChange('reduce')\"></span>";
    htmlContent += "<span id=\"purchaseQuantity\">0</span><span class=\"glyphicon glyphicon-plus-sign\" onclick=\"purchasesQuantityChange('increase')\"></span>";
    htmlContent += "</div><button class=\"btn btn-info\" onclick=\"updatePurchasesList()\" disabled>加入到购物车</button></div>";
    htmlContent += "</div>";
    console.log(htmlContent);
    $("#productInfoDiv").html(htmlContent);
}


$(document).ready(function () {
    $('.icon.expand-icon.glyphicon.glyphicon-plus').parent().unbind("mouseenter");
    $('.icon.expand-icon.glyphicon.glyphicon-plus').parent().mouseenter(function () {
        var nodeid = parseInt($(this).attr('data-nodeid'));
        $('#leftBlockContent').treeview('toggleNodeExpanded', [nodeid, {silent: true}]);
    });
});
