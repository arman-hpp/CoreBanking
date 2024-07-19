package com.bank.services.users;

import cn.apiclub.captcha.Captcha;
import cn.apiclub.captcha.backgrounds.FlatColorBackgroundProducer;
import cn.apiclub.captcha.noise.CurvedLineNoiseProducer;
import cn.apiclub.captcha.text.producer.DefaultTextProducer;
import cn.apiclub.captcha.text.renderer.DefaultWordRenderer;
import com.bank.dtos.users.CaptchaDto;
import com.bank.exceptions.DomainException;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

@Service
public class CaptchaService {

    // Creating Captcha Object
    public CaptchaDto createCaptcha() {
        var captcha = new Captcha.Builder(240, 50)
                .addBackground(new FlatColorBackgroundProducer(Color.WHITE))
                .addText(new DefaultTextProducer(), new DefaultWordRenderer())
                .addNoise(new CurvedLineNoiseProducer())
                .build();

        return new CaptchaDto(encodeCaptcha(captcha), captcha.getAnswer());
    }

    // Converting to binary String
    private String encodeCaptcha(Captcha captcha) {
        try {
            var bos = new ByteArrayOutputStream();
            ImageIO.write(captcha.getImage(), "jpg", bos);
            var byteArray = Base64.getEncoder().encode(bos.toByteArray());
            return new String(byteArray);
        } catch (IOException ex) {
            throw new DomainException("error.public.unexpected");
        }
    }
}
