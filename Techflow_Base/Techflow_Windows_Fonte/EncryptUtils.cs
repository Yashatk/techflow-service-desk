using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Cryptography;
using System.Text;
using System.Threading.Tasks;

namespace Techflow
{
    public class EncryptUtils
    {
        private static readonly Random random = new Random();
        private const string chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        public static string GenerateRandomString(int length)
        {
            return new string(Enumerable.Repeat(chars, length)
                .Select(s => s[random.Next(s.Length)]).ToArray());
        }
        public static string Decrypt(string data)
        {
            if (string.IsNullOrEmpty(data) || data.Length < 50) return "";
                return Decrypt(data.Substring(32), data.Substring(0, 16), data.Substring(16, 16));
        }

        public static string Encrypt(string data)
        {
            if (string.IsNullOrEmpty(data)) return "";
                string sKey = GenerateRandomString(16);
                string ivKey = GenerateRandomString(16);
                string encryptedData = Encrypt(data, ivKey, sKey);
                return sKey + ivKey + encryptedData;
        }

        public static string Encrypt(string data, string ivKey, string sKey)
        {
            using (Aes aes = Aes.Create())
            {
                aes.Key = Encoding.UTF8.GetBytes(sKey);
                aes.IV = Encoding.UTF8.GetBytes(ivKey);
                aes.Mode = CipherMode.CBC;
                aes.Padding = PaddingMode.PKCS7;

                using (ICryptoTransform encryptor = aes.CreateEncryptor())
                {
                    byte[] dataBytes = Encoding.UTF8.GetBytes(data);
                    byte[] encryptedBytes = encryptor.TransformFinalBlock(dataBytes, 0, dataBytes.Length);
                    return Convert.ToBase64String(encryptedBytes);
                }
            }
        }

        public static string Decrypt(string encryptedData, string ivKey, string sKey)
        {
            using (Aes aes = Aes.Create())
            {
                aes.Key = Encoding.UTF8.GetBytes(sKey);
                aes.IV = Encoding.UTF8.GetBytes(ivKey);
                aes.Mode = CipherMode.CBC;
                aes.Padding = PaddingMode.PKCS7;

                using (ICryptoTransform decryptor = aes.CreateDecryptor())
                {
                    byte[] encryptedBytes = Convert.FromBase64String(encryptedData);
                    byte[] decryptedBytes = decryptor.TransformFinalBlock(encryptedBytes, 0, encryptedBytes.Length);
                    // Use UTF-8 explicitamente, igual ao Java
                    return Encoding.UTF8.GetString(decryptedBytes);
                }
            }
        }

        public static string md5(string input)
        {
            using (MD5 md5 = MD5.Create())
            {
                byte[] hashBytes = md5.ComputeHash(Encoding.UTF8.GetBytes(input));
                return BitConverter.ToString(hashBytes).Replace("-", "").ToLower();
            }
        }
    }
}
