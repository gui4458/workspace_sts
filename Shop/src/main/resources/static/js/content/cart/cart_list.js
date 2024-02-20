setFinalPrice();
function chkbAll(){
    const chkAll = document.querySelector('#chkAll');
    const ischk = chkAll.checked;
    const chk = document.querySelectorAll('.chk');
    if(ischk){
        for(const check of chk){
            check.checked = true;
        }
    }    
    else{
        for(const check of chk){
            check.checked = false;
            
        }
    }
    setFinalPrice();
}
// 총 가격 계산 함수
function setFinalPrice(){
    // 체크된 장바구니 상품의 총 가격을 모두 더해서 계산.

    // 클래스가 chk인 태그 중에서 체크가 된 태그만 선택
    const chks = document.querySelectorAll('.chk:checked');
    let sum = 0;
    chks.forEach(function(chk, i){
        // chk 각각의 같은 행에 있는 총 가격 데이터 찾아가기
        const strPrice = chk.closest('tr').children[5].textContent;
        
        // 정규식을 사용해서 쉼표와 원화표시를 제거
        const regex = /[^0-9]/g;
        const price =parseInt(strPrice.replace(regex,'')); // ￦20,000 -> 20000 
        sum = sum + price;
    });

    document.querySelector('#finalPrice-span').textContent ='￦' + sum.toLocaleString() + '원';
    
    // for(const chk of chks){
    //     if(chk.checked){
    //         chkArr.push(chk);
    //     }
    // }
}
function goDelete(cartCode){
    if(confirm('선택한 상품을 장바구니에서 삭제할까요?')){
        location.href=`/cart/delete?cartCode=${cartCode}`
    }
    
}

function goUpdate(btn,cartCode,totalPrice,itemPrice){
   
    const cartCnt = parseInt(btn.parentElement.previousElementSibling.firstElementChild.value);
    // const cartCnt btn.closest('.row').querySelector('input[type="number"]').value;
    totalPrice = itemPrice*cartCnt;
    
    const b =  btn.parentElement.parentElement.parentElement.nextElementSibling;
    
   
    if(confirm('상품 개수를 수정하시겠습니까?')){
        fetch('/cart/cartUpdate', { //요청경로
            method: 'POST',
            cache: 'no-cache',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            //컨트롤러로 전달할 데이터
            body: new URLSearchParams({
                // 데이터명 : 데이터값
                cartCode : cartCode,
                cartCnt : cartCnt,
                totalPrice : totalPrice
            })
        })
            .then((response) => {
                if (!response.ok) {
                    alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
                    return;
                }
    
                return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
                //return response.json(); //나머지 경우에 사용
            })
            //fetch 통신 후 실행 영역
            .then((data) => {//data -> controller에서 리턴되는 데이터!
                b.replaceChildren(b.textContent='￦' + totalPrice.toLocaleString());
                setFinalPrice();
            })
            //fetch 통신 실패 시 실행 영역
            .catch(err => {
                alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
                console.log(err);
            });
    
    }
    
}

function chkDelete(){
    // 만약에 체크된 상품이 하나도 없다면
    // alert으로 '삭제할 상품을 선택하세요'를 띄어주세요
    
    const chks = document.querySelectorAll('.chk:checked');
    if(chks.length == 0){
        alert('삭제할 상품을 선택하세요.');
        return;
    }else{
    
    const chkArr = [];
    for(const chk of chks){
            if(chk.checked){             
                chkArr.push(parseInt(chk.value));
                
            }
    }
    location.href = `/cart/deleteCarts?cartCodeList=${chkArr}`;
    }
}

function chkBuy(items){
    
    const chks = document.querySelectorAll('.chk:checked');
    if(chks.length == 0){
        alert('구매할 상품을 선택하세요.');
        return;
    }
    for(const e of items){
        
        if(e.itemStock-e.cartCnt < 0){
            alert(`재고가 부족합니다.\n상품명 : ${e.itemName}\n남은 수량 : ${e.itemStock}`);
            return;
        }
        
    }
    const cartCodeList = [];
    for(const chk of chks){
        cartCodeList.push(parseInt(chk.value));
    }
    location.href = `/buy/buyCarts?cartCodeList=${cartCodeList}`;
}
