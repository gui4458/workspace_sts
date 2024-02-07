

function goSearch(){
    const select = document.querySelector('#select-buyer-menu').value;
    const text = document.querySelector('.search-text').value;
    console.log(text);


    fetch('/', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
        })
    })
    .then((response) => {
        if(!response.ok){
            alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
            return ;
        }
    
        return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
        //return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}

// goDetail();

// 부트스트랩이 제공하는 모달 태그를 선택하는 방법
const detail_modal = new bootstrap.Modal('#detail-modal');

// 모달 열기
// detail_modal.show();
//  모달 닫기
// detail_modal.hide();

function goDetail(buyCode){



    fetch('/admin/adminBuyDetail', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
           buyCode : buyCode
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

        // 모달 윗부분
        const modalTop = document.querySelector('.detail-modal-body');
    

        modalTop.replaceChildren(modalTop.textContent='');
        const topStr = `
        <div class="row">
            <div class="col">
                <div class="row mb-2">
                    <div class="col">구매번호</div>
                    <div class="col">${data.buyCode}</div>
                    <div class="col">구매자ID</div>
                    <div class="col">${data.memberId}</div>
                </div>
                <div class="row mb-5">
                    <div class="col">구매금액</div>
                    <div class="col">${data.buyPrice}</div>
                    <div class="col">구매일시</div>
                    <div class="col">${data.buyDate}</div>
                </div>
            </div>
        </div>
        `
        modalTop.insertAdjacentHTML('afterbegin',topStr);


        // 모달 아래부분
        let buyDetailList = data.buyDetailList;
        const modalBody = document.querySelector('.modal-body-tag')
        modalBody.replaceChildren(modalBody.textContent='');
        let bodyStr =``
        buyDetailList.forEach(function(buyDetail, i){
            bodyStr +=`
            <tr>
                <td>
                    <div class="row">
                        <div class="col">${buyDetailList.length -i}</div>
                </td>
                <td>
                    <div class="row">
                        <div class="offset-2 col text-start">
                        <img width="20%" src="/upload/${buyDetail.itemVO.imgList[0].attachedFileName}">
                        ${buyDetail.itemVO.itemName}
                        </div>
                </td>
                <td>
                    <div class="row">
                        <div class="col">${buyDetail.buyCnt}</div>
                </td>
                <td>
                    <div class="row">
                        <div class="col">${buyDetail.totalPrice}</div>
                </td>
            </tr>
            `
         });
         modalBody.insertAdjacentHTML('afterbegin',bodyStr);
        detail_modal.show();
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
    

}