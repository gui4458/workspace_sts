
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


function getDetail(itemCode,cates){

    console.log(cates);
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
        console.log(data);
        const detail_div = document.querySelector('.detail-div');
        detail_div.innerHTML = '';

        let str = '';
        str +=`
        <form action="/admin/updateItem" method="post">
        <div class="row">
        <div class="col h3">
            상품 기본 정보
        </div>
        </div>
        <input type="hidden" name="itemCode" value="${data.clickItem.itemCode}" class="form-control">
        <div class="row mb-3">
            <div class="col col-bg">
                <div class="row mb-3">
                    <label class="col-3 col-form-label">카테고리</label>
                    <div class="col-9">
                        <select name="cateCode" class="form-select">`;
                        data.cateList.forEach(cate => {
                            if(cate.cateCode != data.clickItem.cateCode){
                                str +=  `<option value="${cate.cateCode}">${cate.cateName}</option>`;
                            }
                            if(cate.cateCode == data.clickItem.cateCode){
                                str +=  `<option value="${cate.cateCode}" selected>${cate.cateName}</option>`;
                            }
                            
                        });                
        str +=          `</select>
                    </div>
                </div>
                <div class="row mb-3">
                    <label class="col-3 col-form-label">상품명</label>
                    <div class="col-9">
                        <input type="text" name="itemName" value="${data.clickItem.itemName}" class="form-control">
                    </div>
                </div>
                <div class="row mb-3">
                    <label class="col-3 col-form-label">상품수량</label>
                    <div class="col-9">
                        <input type="number" name="itemStock" value="${data.clickItem.itemStock}" class="form-control">
                    </div>
                </div>
                <div class="row mb-3 align-items-center">
                    <label class="col-3 col-form-label">상품상태</label>
                    <div class="col-9">`;
                        
                        switch (data.clickItem.itemStatus) {
                            case 1:
                            str+=    `<div class="form-check form-check-inline">
                            <input class="form-check-input radios" type="radio" name="itemStatus"  checked value="${data.clickItem.itemStatus}">
                            <label class="form-check-label">준비중</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input radios" type="radio" name="itemStatus" value="">
                                <label class="form-check-label">판매중</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input radios" type="radio" name="itemStatus" value="">
                                <label class="form-check-label">매진</label>
                            </div>`
                                break;
                            case 2:
                                str+= `<div class="form-check form-check-inline">
                                <input class="form-check-input radios" type="radio" name="itemStatus"  value="">
                                <label class="form-check-label">준비중</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input radios" type="radio" checked name="itemStatus" value="${data.clickItem.itemStatus}">
                                    <label class="form-check-label">판매중</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input radios" type="radio" name="itemStatus" value="">
                                    <label class="form-check-label">매진</label>
                                </div>`
                                break;
                            default:
                                str+= `<div class="form-check form-check-inline">
                            <input class="form-check-input radios" type="radio" name="itemStatus"  value="">
                            <label class="form-check-label">준비중</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input radios" type="radio" name="itemStatus" value="">
                                <label class="form-check-label">판매중</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input radios" type="radio" checked name="itemStatus" value="${data.clickItem.itemStatus}">
                                <label class="form-check-label">매진</label>
                            </div>`
                          
                        }
                       
                        
                    str+=`</div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col h3">
                상품 이미지 정보
            </div>
        </div>
        <div class="row">
            <div class="col col-bg">
                <div class="row mb-3">
                    <div class="col-3">
                        메인 이미지
                    </div>
                    <div class="col-9" >`;
                    

                    

                    for(const img of data.clickItem.imgList){
                        if(img.isName == 'Y'){
                            str +=`
                            <span onclick="goImgModal('${img.attachedFileName}')">${img.originFileName}</span>
                            `
                        }

                    }   
                str+=`</div>
                </div>
                <div class="row mb-3">
                    <div class="col-3">
                        상세 이미지
                    </div>`;

                const i = 0;
                data.clickItem.imgList.forEach(function(img){
                
                    if(img.isName == 'N'){
                        if(i == 0){
                            str += `
                            <div class="col-9">
                            <span onclick="goImgModal('${img.attachedFileName}')">${img.originFileName}</span>
                            </div>`;
                        }else{
                            str += `
                            <div class="offset-3 col-9">
                            <span onclick="goImgModal('${img.attachedFileName}')">${img.originFileName}</span>
                            </div>`;
                        }
                       
                    }
                });

                
                    
                str += `</div>
            </div>
        </div>
        <div class="row">
            <div class="col-4 d-grid">
                <input type="submit" value="변 경"class="btn btn-primary">
            </div>
        </div>
        </form>
        `;

        detail_div.insertAdjacentHTML('afterbegin', str );
        const radios = document.querySelectorAll('.radios')
        radios.forEach(e => {   
            if(e.value == data.itemStatus){
                e.checked=true;
            }
        });
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}


function goImgModal(img){
    
    const img_modal = new bootstrap.Modal('#img-modal');
    const img_modal_body = document.querySelector('.img-modal-body');
    img_modal_body.innerHTML='';
    img_modal_body.insertAdjacentHTML('afterbegin',`<img width="100%" src="/upload/${img}">`)
    img_modal.show();
}