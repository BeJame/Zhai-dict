package org.cloudplayer.neetwords.controller;

import java.util.HashMap;
import java.util.Map;

public class BookController {
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;

    /**
     * TODO 获取用户数据
     */
    @RequestMapping(value = "/books/info",produces = "application/json;charset=utf-8",method= RequestMethod.GET)
    @ResponseBody
    public String getData(@RequestParam("id")int id) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        User user = userService.get(id);
        Book book = bookService.get(user.getBookid());
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("bookId",user.getBookid());
        map.put("bookName",book.getName());
        map.put("bookImage",book.getAddress());
        map.put("totalWords",book.getNum());
        map.put("learnedWords",user.getLearned());
        String str = mapper.writeValueAsString(map);
        return str;
    }
}
