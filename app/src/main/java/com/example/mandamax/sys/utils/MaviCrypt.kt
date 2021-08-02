package com.example.mandamax.sys.utils


import android.util.Base64
import android.util.Log
import java.security.InvalidAlgorithmParameterException
import java.security.InvalidKeyException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import javax.crypto.*
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec


class MaviCrypt {


    private val TAG = MaviCrypt::class.java.simpleName
    private val ALGORITHM = "AES"
    private lateinit var _cipher: Cipher
    private lateinit var _password: SecretKey
    private lateinit var _IVParamSpec:IvParameterSpec
    //16-byte private key
    private val IV: ByteArray = byteArrayOf(74, 68, 65, 30, 20, 6.0.toByte(), 61, 76, 31, 20, 53, 74, 30, 72, 33, 44)

    init{
        try
        {
            val pass = byteArrayOf(63, 72, 38, 70, 74, 20, 74, 6f.toByte(), 5f.toByte(), 70, 61, 73, 73, 31, 31, 36)
            //Encode digest
            val digest:MessageDigest
            val DIGEST = "MD5"
            digest = MessageDigest.getInstance(DIGEST)
            _password = SecretKeySpec(digest.digest(pass), ALGORITHM)
            //Initialize objects
            val TRANSFORMATION = "AES/CBC/PKCS7Padding"
            _cipher = Cipher.getInstance(TRANSFORMATION)
            _IVParamSpec = IvParameterSpec(IV)
        }
        catch (e: NoSuchAlgorithmException) {
            Log.e(TAG, "No such algorithm " + ALGORITHM, e)
        }
        catch (e: NoSuchPaddingException) {
            Log.e(TAG, "No such padding PKCS7", e)
        }
    }
    /**
     * Encryptor.
     *
     * @param text String to be encrypted
     * @return Base64 encrypted text
     */
    fun encrypt(text:ByteArray):String {
        val encryptedData:ByteArray
        try
        {
            _cipher.init(Cipher.ENCRYPT_MODE, _password, _IVParamSpec)
            encryptedData = _cipher.doFinal(text)
        }
        catch (e: InvalidKeyException) {
            Log.e(TAG, "Invalid key (invalid encoding, wrong length, uninitialized, etc).", e)
            return ""
        }
        catch (e: InvalidAlgorithmParameterException) {
            Log.e(TAG, "Invalid or inappropriate algorithm parameters for " + ALGORITHM, e)
            return ""
        }
        catch (e: IllegalBlockSizeException) {
            Log.e(TAG, "The length of data provided to a block cipher is incorrect", e)
            return ""
        }
        catch (e: BadPaddingException) {
            Log.e(TAG, "The input data but the data is not padded properly.", e)
            return ""
        }
        return Base64.encodeToString(encryptedData, Base64.DEFAULT)
    }
    /**
     * Decryptor.
     *
     * @param text Base64 string to be decrypted
     * @return decrypted text
     */
    fun decrypt(text:String):String? {
        try
        {
            _cipher.init(Cipher.DECRYPT_MODE, _password, _IVParamSpec)
            val decodedValue = Base64.decode(text.toByteArray(), Base64.DEFAULT)
            val decryptedVal = _cipher.doFinal(decodedValue)
            return String(decryptedVal)
        }
        catch (e: InvalidKeyException) {
            Log.e(TAG, "Invalid key (invalid encoding, wrong length, uninitialized, etc).", e)
            return null
        }
        catch (e: InvalidAlgorithmParameterException) {
            Log.e(TAG, "Invalid or inappropriate algorithm parameters for " + ALGORITHM, e)
            return null
        }
        catch (e: IllegalBlockSizeException) {
            Log.e(TAG, "The length of data provided to a block cipher is incorrect", e)
            return null
        }
        catch (e: BadPaddingException) {
            Log.e(TAG, "The input data but the data is not padded properly.", e)
            return null
        }
    }
}