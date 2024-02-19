function goInsert(){

    const modelName = document.querySelector('#modelName').value;
    const price = document.querySelector('#price').value;

    if(modelName != '' && price !=''){
    document.querySelector('#insert-btn').submit();
        
    }
    if(modelName == ''){
        alert('모델명을 입력해주세요.')
    }
    if(price == ''){
        alert('가격을 입력해주세요.')
    } 

}