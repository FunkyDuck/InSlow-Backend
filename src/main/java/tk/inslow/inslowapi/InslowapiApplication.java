package tk.inslow.inslowapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication
public class InslowapiApplication {

	public static void main(String[] args) {
		String logo = 	"     __          ______  //\n" +
				"    //  ______  //      //    ______  __   ___   __         ______\n" +
				"   //  //  //  //==//  //    //  //  //  // //  //         /      \\      /°/°\n" +
				"  //  //  //      //  //    //  //  // //  // //          |   ()   |    / /\n" +
				" //  //  //  ====//  //==  //==//  ////   ////         ____\\      /__-----\\" +
				"\n" +
				"//  -----------------------------------------        °=___________________/\n" +
				"  >> InSlow >> API v0.1";

		System.out.println(logo);
		SpringApplication.run(InslowapiApplication.class, args);
	}

}
