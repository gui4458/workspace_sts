package com.green.Shop.admin.controller;

import com.green.Shop.admin.service.AdminServiceImpl;
import com.green.Shop.buy.service.BuyServiceImpl;
import com.green.Shop.buy.vo.BuyVO;
import com.green.Shop.item.service.ItemServiceImpl;
import com.green.Shop.item.vo.CategoryVO;
import com.green.Shop.item.vo.ImgVO;
import com.green.Shop.item.vo.ItemVO;
import com.green.Shop.member.vo.MemberVO;
import com.green.Shop.search.vo.SearchVO;
import com.green.Shop.util.ConstantVariable;
import com.green.Shop.util.UploadUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Resource(name="adminService")
    private AdminServiceImpl adminService;
    @Resource(name="itemService")
    private ItemServiceImpl itemService;
    @Resource(name="buyService")
    private BuyServiceImpl buyService;
//    상품 등록 페이지로 이동
    @GetMapping("/regItemForm")
    public String regItemForm(Model model
                                ,@RequestParam(name="page", required = false, defaultValue = "regItem")String page){
        model.addAttribute("page",page);
        model.addAttribute("cateList",itemService.selectCategory());
        return "content/admin/reg_item_form";
    }

    @PostMapping("/insertItem")
    public String insertItem(ItemVO itemVO, Model model
                            ,@RequestParam(name ="mainImg") MultipartFile mainImg
                            ,@RequestParam(name="detailImg") MultipartFile[] detailImg){


//      ======= 파일 첨부 =======
//      메인 이미지 하나 업로드
        ImgVO mainImgVO = UploadUtil.uploadFile(mainImg);

//      상세 이미지들 업로드
        List<ImgVO> imgList = UploadUtil.multiUploadFile(detailImg);

//      ======= 다음에 들어갈 ITEM_CODE 조회 =======
        int itemCode = adminService.selectNextItemCode();
         itemVO.setItemCode(itemCode);



//        model.addAttribute(itemVO);


////      ======= 내꺼 =======
////      ===== 단일 =====
//        String fileName = ConstantVariable.UPLOAD_PATH + mainImg.getOriginalFilename();
//        String extension1 = fileName.substring(fileName.lastIndexOf("."));
//        String attachedFileName1 = UUID.randomUUID().toString() + extension1;
//        try {
//            File file1 = new File(ConstantVariable.UPLOAD_PATH + attachedFileName1);
//
//            mainImg.transferTo(file1);
//        }catch (Exception e) {
//            System.out.println("파일 첨부 중 오류 발생");
//            e.printStackTrace();
//        }
////      ===== 다중 =====
//        for (MultipartFile img : detailImg){
//            String extension = img.getOriginalFilename().substring(img.getOriginalFilename().lastIndexOf("."));
//            String attachedFileName = UUID.randomUUID().toString() + extension;
//
//            try {
//                File file = new File(ConstantVariable.UPLOAD_PATH + attachedFileName);
//                img.transferTo(file);
//            }catch (Exception e){
//                System.out.println("디테일 첨부 오류");
//                e.printStackTrace();
//            }
//        }
//        ======== 상품 이미지 정보 등록 쿼리 실행 ========

//        이미지 등록시 채워줘야하는 모든 데이터를 갖는 리스트를 생성
//        mainImg.getOriginalFilename();

        mainImgVO.setItemCode(itemCode);
        for (ImgVO img : imgList){
            img.setItemCode(itemCode);
        }
        imgList.add(mainImgVO);
        itemVO.setImgList(imgList);
//      ======= 상품등록 =======
        adminService.insertItem(itemVO);

        return "redirect:/admin/regItemForm";
    }


    @RequestMapping("/adminBuyList")
    public String adminSelectBuyList(Model model, SearchVO searchVO
                                    ,@RequestParam(name="page")String page){
        List<BuyVO> buyList = adminService.selectBuyList(searchVO);
        model.addAttribute("buyList",buyList);
        System.out.println("@@@@@@@@@"+searchVO);
        model.addAttribute("page",page);
        return "content/admin/admin_history";
    }
    @ResponseBody
    @PostMapping("/adminBuyDetail")
    public BuyVO adminBuyDetail(BuyVO buyVO){
        BuyVO detail = adminService.selectDetailBuyList(buyVO);
        System.out.println(detail);
        return detail;

    }
// 업데이트화면
    @GetMapping("/updateItem")
    public String updateItem(Model model
                            ,@RequestParam(name="page")String page){
        List<ItemVO> itemInfo = adminService.selectUpdateInfo();
        model.addAttribute("itemInfo",itemInfo);

        List<CategoryVO> cates = adminService.cateName();
        model.addAttribute("cates",cates);
        model.addAttribute("page",page);
        System.out.println(itemInfo);
        return "content/admin/update_item";
    }
    @ResponseBody
    @PostMapping("/clickItemInfo")
    public ItemVO clickItemInfo(ItemVO itemVO){
        ItemVO clickItem = adminService.clickItemInfo(itemVO);

        return clickItem;
    }

    @ResponseBody
    @PostMapping("/updateItem")
    public void updateItem(ItemVO itemVO){
        adminService.updateItem(itemVO);

    }






}
