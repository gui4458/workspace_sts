
function upDateItem(itemCode,cateCode,cates){
    const selectBoxTag = document.querySelector('#selectBox')
    const itemCnt = document.querySelector('#itemCnt')
    const itemName = document.querySelector('#itemName')
    const mainImg = document.querySelector('.mainImg')
    const radios = document.querySelectorAll('.radios')
    
    
    fetch('/admin/clickItemInfo', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
           itemCode : itemCode
        })
    })
    .then((response) => {
        if(!response.ok){
            alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
            return ;
        }
    
        // return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
        return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        
        // 알맞은 radio 체크하기
        radios.forEach(e => {   
            if(e.value == data.itemStatus){
                e.checked=true;
            }
        });

        let option =``
        // 셀렉트박스
        selectBoxTag.replaceChildren();
        cates.forEach(function(cate, i){
            if(cates.cateCode == cateCode){
                option += `<option value="cate.cateCode" selected>${cate.cateName}</option>`
            }
            if(cates.cateCode != cateCode){
                option += `<option value="cate.cateCode">${cate.cateName}</option>`
            }
            
        });
        
        selectBoxTag.insertAdjacentHTML('afterbegin',option);
        // 인풋에 상품 수량 삽입
        itemCnt.value=data.itemStock;
        // 인풋에 아이템 이름 삽입
        itemName.value=data.itemName;
        // 이미지 뽑아서 넣기
        let imgList =data.imgList;
        let modalStr = ``
        let subImgModal = ``
        imgList.forEach(function(img, i){
            if (img.isName=='Y') {
                mainImg.textContent=img.originFileName;
                modalStr = `<img width="100%" src="/upload/${img.attachedFileName}">`
            }
            if (img.isName=='N') {
                document.querySelector('.subImg').textContent=img.originFileName;
                subImgModal = `<img width="100%" src="/upload/${img.attachedFileName}">`
            }
            
        });
        const modal = document.querySelector('.img-modal');

        modal.replaceChildren();

        
    modal.insertAdjacentHTML('afterbegin',modalStr)

    


    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}


function goImage(itemCode){
    const mainImg_modal = new bootstrap.Modal('#mainImg-modal');
    console.log(itemCode);
    mainImg_modal.show();
}


function updateItem(){

    document.querySelector('#updateForm').submit();

}