package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository; // Autowired로 DI됨

    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);

        return "/basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model){
        Item findItem = itemRepository.findById(itemId);
        model.addAttribute("item", findItem);
        return "/basic/item";
    }

    @GetMapping("/add")
    public String addForm(){
        return "/basic/addForm";
    }

//    @PostMapping("/add") // 같은 url but 메서드 구분
//    public String addItemV1(@RequestParam String itemName,
//                       @RequestParam int price,
//                       @RequestParam Integer quantity,
//                       Model model){
//        Item item = new Item();
//        item.setItemName(itemName);
//        item.setPrice(price);
//        item.setQuantity(quantity);
//
//        itemRepository.save(item);
//
//        model.addAttribute("item", item);
//
////        return "/basic/addItem";
//        return "/basic/item";
//    }
//
//    @PostMapping("/add") // 같은 url but 메서드 구분
//    public String addItemV2(@ModelAttribute("item") Item item){
//
//        itemRepository.save(item); // 자동 추가 생략 가능
//
//        return "/basic/item";
//    }
//
//    @PostMapping("/add") // 같은 url but 메서드 구분
//    public String addItemV3(@ModelAttribute Item item){
//        // 클래스명에서 Model 데이터 변수명을 유추함 (Item -> item)
//        itemRepository.save(item);
//
//        return "/basic/item";
//    }
//
//    @PostMapping("/add") // 같은 url but 메서드 구분
//    public String addItemV4(Item item){
//        // 클래스명에서 Model 데이터 변수명을 유추함 (Item -> item)
//        itemRepository.save(item);
//
//        return "/basic/item";
//    }

//    @PostMapping("/add") // 같은 url but 메서드 구분
//    public String addItemV5(Item item){
//
//        itemRepository.save(item); // 자동 추가 생략 가능
//
//        return "redirect:/basic/items/" + item.getId();
////        return "/basic/item";
//    }

    @PostMapping("/add") // 같은 url but 메서드 구분
    public String addItemV6(Item item, RedirectAttributes redirectAttributes){

        Item savedItem = itemRepository.save(item);// 자동 추가 생략 가능
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);

        return "redirect:/basic/items/{itemId}";

    }
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model){
        Item findItem = itemRepository.findById(itemId);
        model.addAttribute("item", findItem);
        return "/basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item){
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
        // 상품 상세로 돌아가도록, 왜 Redirect 햇는진 다음에..
    }

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init(){
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }
}
