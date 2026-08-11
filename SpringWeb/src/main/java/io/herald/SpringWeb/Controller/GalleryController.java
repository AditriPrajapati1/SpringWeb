package io.herald.SpringWeb.Controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import io.herald.SpringWeb.Model.ImageTable;
import io.herald.SpringWeb.Model.ImageTable2;
import io.herald.SpringWeb.Repository.Image2Repository;
import io.herald.SpringWeb.Repository.ImageRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;

@Controller
public class GalleryController {

    @Autowired
    private ImageRepository imgRepo;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private Image2Repository image2Repo;

    @GetMapping("/gallery")
    public String galleryGet(HttpServletRequest request, Model m) {
        HttpSession session = request.getSession();

        if (session.getAttribute("username") == null) {
            m.addAttribute("message", "You are not logged in");
            return "loginPage";
        }

        return "galleryPage";
    }

    @PostMapping("/galleryPost")
    public String galleryPost(@RequestParam("imgFile") MultipartFile imgFile, HttpSession session) {
        try {
            byte[] imgBytes = imgFile.getBytes();

            String imgString = Base64.getEncoder().encodeToString(imgBytes);

            ImageTable img = new ImageTable();
            img.setImage(imgString);

            imgRepo.save(img);
        } catch (IOException e) {
            e.printStackTrace();
        }

        session.setAttribute("totalImages", imgRepo.findAll());
        return "galleryPage";
    }

    @GetMapping("/gallery2")
    public String gallery2Get(Model m)
    {
        m.addAttribute("cloudImages",image2Repo.findAll());
        return "galleryPage2";
    }

    @PostMapping("/gallery2")
    public String gallery2Post(@RequestParam("img") MultipartFile image, Model m) {
        try {
            Map uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
            String imgUrl = uploadResult.get("secure_url").toString();

            ImageTable2 img = new ImageTable2();
            img.setImageUrl(imgUrl);

            image2Repo.save(img);

        } catch (IOException e) {
            e.printStackTrace();
        }

        m.addAttribute("cloudImages", image2Repo.findAll());

        return "galleryPage2";
    }
}
