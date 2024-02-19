function goSalesInsert(){
    const buyerName = document.querySelector('#buyerName').value; 
    if(buyerName != ''){
        document.querySelector('#salesForm').submit();
    }
    alert('구매자명을 입력해주세요.');
}