// package nus.iss.paf.miniproject.controllers;

// @RestController
// @RequestMapping("/search")
// public class CardSearchController {

//     @Autowired
//     private CardService cardSvc;

//     @GetMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//     public ModelAndView getCards(@RequestBody String payload) throws IOException {

//         JsonObject req;

//         try (InputStream is = new ByteArrayInputStream(payload.getBytes())) {
//             JsonReader reader = Json.createReader(is);
//             req = reader.readObject();
//         }

//         String fname = req.getString("fname");

//         ModelAndView mvc = new ModelAndView();

//         List<String> images = cardSvc.getCards(fname);

//         mvc.addObject("fname", fname);
//         mvc.addObject("images", images);
//         mvc.setViewName("result");

//         return mvc;
//     }
    
// }
