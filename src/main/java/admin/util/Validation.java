package admin.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.shiro.codec.Base64;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONObject;

import admin.entity.PageBean;
import admin.entity.api.Card;

public class Validation {

	// 注册前是否合法验证
	/**
	 * 用户ID验证（包含0~9的数字且长度必须在[1, 15]）
	 * 
	 * @param userName
	 * @return
	 */
	public static boolean validateName(String userName) {
		String reg = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,10}$";

		if (!userName.matches(reg)) {
			return false;
		}

		return true;
	}

	/**
	 * 验证手机号码
	 * 
	 * @param mobile
	 * @return
	 */
	public static boolean validateMobile(String mobile) {
		if (mobile.equals(null)) {
			return false;
			// 验证手机号码格式是否正确
		} else if (!mobile.matches("^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0-9]))\\d{8}$")) {

			return false;
		}
		return true;
	}

	/**
	 * 密码验证（只能是英文字符且长度必须在[6~10]）
	 * 
	 * @param userPwd
	 * @return
	 */
	public static boolean validatePwd(String userPwd) {
		String reg = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,10}$";

		if (!userPwd.matches(reg)) {
			return false;
		}
		return true;
	}

	/**
	 * 返回token等数据
	 * 
	 * @param response
	 * @param token
	 * @param code
	 * @param msg
	 * @param data
	 * @throws Exception
	 */
	public static void postJson(HttpServletResponse response, Object token, Object code, Object msg, Object data)
			throws Exception {
		JSONObject json = new JSONObject();
		json.put("code", code);
		json.put("token", token);
		json.put("msg", msg);
		json.put("data", data);
		ResponseUtil.write(response, json);
	}

	/**
	 * 接收移动端的二进制流并生成图片
	 * 
	 * @param request
	 * @param image
	 * @return
	 * @throws IOException
	 */
	public static String getImagesUrl(HttpServletRequest request, String image) throws IOException {

		// 定义变量存储图片地址
		String imagePath = "";
		// 将base64 转 字节数组
		Base64 base = new Base64();
		byte[] decode = base.decode(image);
		String imageUrl = "/userImages/user/" + UUID.randomUUID() + ".png";
		// 图片输出路径
		imagePath = request.getServletContext().getRealPath("/") + imageUrl;

		// 定义图片输入流
		InputStream fin = new ByteArrayInputStream(decode);
		// 定义图片输出流
		FileOutputStream fout = new FileOutputStream(imagePath);
		// 写文件
		byte[] b = new byte[1024];
		int length = 0;
		while ((length = fin.read(b)) > 0) {

			fout.write(b, 0, length);
		}

		// 关闭数据流
		fin.close();
		fout.close();

		return imageUrl;
	}

	/**
	 * 成员图像进行合成拼接成新的图片返回给群
	 * 
	 * @param files
	 * @param type
	 * @param targetFile
	 */
	public static boolean getCombinationOfhead(List<String> paths, String dir) throws IOException {
		List<BufferedImage> bufferedImages = new ArrayList<BufferedImage>();
		// 压缩图片所有的图片生成尺寸同意的 为 50x50
		for (int i = 0; i < paths.size(); i++) {
			BufferedImage resize2 = resize2(paths.get(i), 40, 40, true);
			bufferedImages.add(resize2);
		}

		int width = 112; // 这是画板的宽高

		int height = 112; // 这是画板的高度

		// BufferedImage.TYPE_INT_RGB可以自己定义可查看API

		BufferedImage outImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		// 生成画布
		Graphics g = outImage.getGraphics();

		Graphics2D g2d = (Graphics2D) g;

		// 设置背景色
		g2d.setBackground(new Color(231, 231, 231));

		// 通过使用当前绘图表面的背景色进行填充来清除指定的矩形。
		g2d.clearRect(0, 0, width, height);

		// 开始拼凑 根据图片的数量判断该生成那种样式的组合头像目前为4中
		int j = 1;
		for (int i = 1; i <= bufferedImages.size(); i++) {
			if (bufferedImages.size() == 9) {
				if (i <= 3) {
					g2d.drawImage(bufferedImages.get(i - 1), 30 * i + 3 * i - 31, 0, null);
				} else if (i <= 6) {
					g2d.drawImage(bufferedImages.get(i - 1), 27 * j + 5 * j - 30, 36, null);
					j++;
				} else {
					g2d.drawImage(bufferedImages.get(i - 1), 27 * j + 5 * j - 125, 77, null);
					j++;
				}
			} else if (bufferedImages.size() == 8) {
				if (i <= 3) {
					g2d.drawImage(bufferedImages.get(i - 1), 30 * i + 3 * i - 31, 0, null);
				} else if (i <= 6) {
					g2d.drawImage(bufferedImages.get(i - 1), 27 * j + 5 * j - 30, 36, null);
					j++;
				} else {
					g2d.drawImage(bufferedImages.get(i - 1), 21 * j + 9 * j - 100, 77, null);
					j++;
				}

			}

			else if (bufferedImages.size() == 7) {
				if (i <= 3) {
					g2d.drawImage(bufferedImages.get(i - 1), 30 * i + 3 * i - 31, 0, null);
				} else if (i <= 6) {
					g2d.drawImage(bufferedImages.get(i - 1), 27 * j + 5 * j - 30, 36, null);
					j++;
				} else {
					g2d.drawImage(bufferedImages.get(i - 1), 3, 75, null);
				}

			}

			else if (bufferedImages.size() == 6) {
				if (i <= 3) {
					g2d.drawImage(bufferedImages.get(i - 1), 30 * i + 3 * i - 31, 10, null);
				} else {
					g2d.drawImage(bufferedImages.get(i - 1), 31 * j + 4 * j - 33, 60, null);
					j++;
				}
			} else if (bufferedImages.size() == 5) {
				if (i <= 3) {
					g2d.drawImage(bufferedImages.get(i - 1), 30 * i + 3 * i - 28, 10, null);
				} else {
					g2d.drawImage(bufferedImages.get(i - 1), 48 * j + 8 * j - 49, 60, null);
					j++;
				}

			} else if (bufferedImages.size() == 4) {
				if (i <= 2) {
					g2d.drawImage(bufferedImages.get(i - 1), 50 * i + 4 * i - 50, 4, null);
				} else {
					g2d.drawImage(bufferedImages.get(i - 1), 50 * j + 4 * j - 50, 58, null);
					j++;
				}
			} else if (bufferedImages.size() == 3) {
				if (i <= 1) {

					g2d.drawImage(bufferedImages.get(i - 1), 31, 4, null);

				} else {

					g2d.drawImage(bufferedImages.get(i - 1), 50 * j + 4 * j - 50, 58, null);

					j++;
				}

			} else if (bufferedImages.size() == 2) {

				g2d.drawImage(bufferedImages.get(i - 1), 50 * i + 4 * i - 50, 31, null);

			} else if (bufferedImages.size() == 1) {

				g2d.drawImage(bufferedImages.get(i - 1), 31, 31, null);

			}

			// 需要改变颜色的话在这里绘上颜色。可能会用到AlphaComposite类
		}

		// StringBuffer outPath = new StringBuffer().append(dir)
		// .append("groupPicture")
		// .append(File.separatorChar)
		// .append(groupId.substring(0, 4))
		// .append(File.separatorChar)
		// .append(groupId).append(".jpg");
		//
		String format = "png";
		File file = new File(dir);
		if (!file.exists()) {
			file.mkdirs();
		}

		return ImageIO.write(outImage, format, file);
	}

	/**
	 * 读取传入的用户图片
	 * 
	 * @param filePath
	 * @param height
	 * @param width
	 * @param bb
	 * @return
	 */
	public static BufferedImage resize2(String filePath, int height, int width, boolean bb) {
		try {
			double ratio = 0; // 缩放比例
			// System.out.println("图片缩放"+filePath);
			BufferedImage bi = null;
			if (filePath.indexOf("http://") == 0) {
				bi = ImageIO.read(new URL(filePath));
			} else {
				bi = ImageIO.read(new File(filePath));
			}
			Image itemp = bi.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			// 计算比例
			if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
				if (bi.getHeight() > bi.getWidth()) {
					ratio = (new Integer(height)).doubleValue() / bi.getHeight();
				} else {
					ratio = (new Integer(width)).doubleValue() / bi.getWidth();
				}
				AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);
				itemp = op.filter(bi, null);
			}
			if (bb) {
				// copyimg(filePath, "D:\\img");
				BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
				Graphics2D g = image.createGraphics();
				g.setColor(Color.white);
				g.fillRect(0, 0, width, height);
				if (width == itemp.getWidth(null))
					g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2, itemp.getWidth(null),
							itemp.getHeight(null), Color.white, null);
				else
					g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0, itemp.getWidth(null),
							itemp.getHeight(null), Color.white, null);
				g.dispose();
				itemp = image;
			}
			return (BufferedImage) itemp;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 文件上传
	 * 
	 * @param request
	 * @return
	 */
	public static String uploadFile( String saveDir, MultipartFile multipartFile) {
		//MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		//MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		//MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
		
		String flag="false";
		/** 得到图片保存目录的真实路径 **/
		String logoRealPathDir = saveDir;// String saveDir = "D:\\test";

		/** 根据真实路径创建目录 **/
		File logoSaveFile = new File(logoRealPathDir);
		if (!logoSaveFile.exists()) {
			logoSaveFile.mkdirs();
		}

		/** 页面控件的文件流 **/
		//MultipartFile multipartFile = multipartRequest.getFile(fileElementId);

		/** 获取文件的后缀 **/
		String filenameString = multipartFile.getOriginalFilename();
		System.out.println(filenameString);
		String suffix = multipartFile.getOriginalFilename()
				.substring(multipartFile.getOriginalFilename().lastIndexOf("."));

		/** 拼成完整的文件保存路径加文件 **/
		String name = UUID.randomUUID()+ suffix;// suffix;
		String fileName = logoRealPathDir + File.separator + name;
		File file = new File(fileName);
		String data = file.getPath();

		try {
			multipartFile.transferTo(file);
			flag=name;
		
		} catch (IllegalStateException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		return flag;

	}
}
