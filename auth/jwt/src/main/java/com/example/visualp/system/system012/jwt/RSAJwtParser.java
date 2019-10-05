package com.example.visualp.system.system012.jwt;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.ParseException;
import java.util.Base64;
import javax.annotation.Nonnull;
import org.springframework.stereotype.Component;

/**
 * 自動で生成する場合は以下.
 *
 * <pre>
 *   KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
 *   keyGenerator.initialize(2048);
 *
 *   KeyPair kp = keyGenerator.genKeyPair();
 *   RSAPublicKey publicKey = (RSAPublicKey) kp.getPublic();
 *   RSAPrivateKey privateKey = (RSAPrivateKey) kp.getPrivate();
 * </pre>
 */
@Component
public class RSAJwtParser {

  // Java で Jwt 生成する場合、OpenSSH で生成した RSA 鍵をそのまま読み込めるライブラリがない (らしい) ので、
  // PEM 変換 -> 公開鍵生成 -> 秘密鍵を PKC8 形式に変換した
  //
  // 参考: (https://gist.github.com/destan/b708d11bd4f403506d6d5bb5fe6a82c5)
  // コマンド:
  // > ssh-keygen -t rsa -b 2048
  // > openssl rsa -pubout -in private_key.pem -out public_key.pem
  // > openssl pkcs8 -topk8 -in private_key.pem -inform pem -out private_key_pkcs8.pem -outform pem -nocrypt
  private final String PEM_PUBLIC_KEY = "";
  private final String PKCS_PRIVATE_KEY = "";

  /**
   * 暗号化.
   */
  @Nonnull
  public String encrypt(
      @Nonnull String payload
  ) throws NoSuchAlgorithmException, InvalidKeySpecException, JOSEException {
    JWSObject jwsObject = createJwtObject(payload);

    jwsObject.sign(createJwtSign(PKCS_PRIVATE_KEY));
    return jwsObject.serialize();
  }

  @Nonnull
  private JWSObject createJwtObject(@Nonnull String payload) {
    JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.RS256);

    // Verify 時にヘッダ情報のチェックが行われている。
    // 超過している場合にエラーになるケースがあった。
    // 参照: https://hiyosi.tumblr.com/post/70073770678/jwt%E3%81%AB%E3%81%A4%E3%81%84%E3%81%A6%E7%B0%A1%E5%8D%98%E3%81%AB%E3%81%BE%E3%81%A8%E3%82%81%E3%81%A6%E3%81%BF%E3%81%9F
    // ※ kid 入れると verify に失敗したので一旦何もいれてない。
    return new JWSObject(
        jwsHeader,
        new Payload(payload)
    );
  }

  @Nonnull
  private JWSSigner createJwtSign(
      @Nonnull String privateKey
  ) throws NoSuchAlgorithmException, InvalidKeySpecException {
    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey));

    KeyFactory kf = KeyFactory.getInstance("RSA");
    RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) kf.generatePrivate(keySpec);

    return new RSASSASigner(rsaPrivateKey);
  }


  /**
   * 復号/検証.
   */
  @Nonnull
  public Jwt decrypt(
      @Nonnull String encryptedJwt
  ) throws NoSuchAlgorithmException, InvalidKeySpecException, ParseException, JOSEException {
    JWSObject decodeObject = JWSObject.parse(encryptedJwt);

    if (!decodeObject.verify(createJWSVerifier(PEM_PUBLIC_KEY))) {
      throw new IllegalStateException();
    }
    return Jwt.builder()
        .header(decodeObject.getHeader().toString())
        .payload(decodeObject.getPayload().toString())
        .sign(decodeObject.getSignature().toString())
        .build();
  }

  @Nonnull
  private JWSVerifier createJWSVerifier(
      @Nonnull String publicKey
  ) throws NoSuchAlgorithmException, InvalidKeySpecException {
    KeyFactory kf = KeyFactory.getInstance("RSA");

    // 発端として、以下のようなコードを参考に作ろうとした。
    // <pre>
    //     KeySpec keySpec = new X509EncodedKeySpec(
    //        BaseEncoding.base16().decode(keyString.toUpperCase())
    //    );
    // </pre>
    // ※ keyString は PEM の公開鍵を 16 進数で encode した値
    //
    // どうやっても↑が失敗する (InvalidKeySpecException が発生する) ので
    // ↑の方法でない方法で実験している。
    // ※ ↑のコードでなんで動いているのかわからないが、もし RSA 公開鍵としておかしい場合、
    //    InvalidKeySpecException が発生するので、動いているのはたぶん間違いない。
    KeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey));
    RSAPublicKey rsaPublicKey = (RSAPublicKey) kf.generatePublic(keySpec);

    return new RSASSAVerifier(rsaPublicKey);
  }
}
